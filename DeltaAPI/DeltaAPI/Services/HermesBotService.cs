using System.Net.Http.Json;
using System.Text.Json;
using Microsoft.Extensions.Options;

namespace DeltaAPI.Services
{
    // Opções mapeadas do appsettings.json -> seção "HermesBot"
    public class HermesBotOptions
    {
        public string Endpoint { get; set; } = string.Empty;       // ex: https://delta-language-service.cognitiveservices.azure.com/
        public string ApiKey { get; set; } = string.Empty;         // chave do Azure
        public string ProjectName { get; set; } = string.Empty;    // ex: Hermes-Bot
        public string DeploymentName { get; set; } = "production"; // ex: production
    }

    public class HermesBotService
    {
        private readonly HttpClient _httpClient;
        private readonly HermesBotOptions _options;

        public HermesBotService(HttpClient httpClient, IOptions<HermesBotOptions> options)
        {
            _httpClient = httpClient;
            _options = options.Value;

            if (string.IsNullOrWhiteSpace(_options.Endpoint))
                throw new ArgumentException("A opção HermesBot:Endpoint não está configurada.");

            if (string.IsNullOrWhiteSpace(_options.ApiKey))
                throw new ArgumentException("A opção HermesBot:ApiKey não está configurada.");

            if (string.IsNullOrWhiteSpace(_options.ProjectName))
                throw new ArgumentException("A opção HermesBot:ProjectName não está configurada.");

            if (string.IsNullOrWhiteSpace(_options.DeploymentName))
                _options.DeploymentName = "production";

            // Configuração do HttpClient base
            _httpClient.BaseAddress = new Uri(_options.Endpoint);

            // Cabeçalho de autenticação do Azure Question Answering
            if (!_httpClient.DefaultRequestHeaders.Contains("Ocp-Apim-Subscription-Key"))
            {
                _httpClient.DefaultRequestHeaders.Add("Ocp-Apim-Subscription-Key", _options.ApiKey);
            }
        }

        public async Task<string> PerguntarAoHermesAsync(string pergunta, CancellationToken cancellationToken = default)
        {
            if (string.IsNullOrWhiteSpace(pergunta))
                throw new ArgumentException("A pergunta não pode ser vazia.", nameof(pergunta));

            // Rota do serviço de Question Answering
            var uri =
                $"/language/:query-knowledgebases" +
                $"?projectName={Uri.EscapeDataString(_options.ProjectName)}" +
                $"&api-version=2021-10-01" +
                $"&deploymentName={Uri.EscapeDataString(_options.DeploymentName)}";

            var payload = new
            {
                top = 1,
                question = pergunta
            };

            using var response = await _httpClient.PostAsJsonAsync(uri, payload, cancellationToken);

            if (!response.IsSuccessStatusCode)
            {
                var errorText = await response.Content.ReadAsStringAsync(cancellationToken);
                throw new InvalidOperationException(
                    $"Erro ao chamar o Hermes-Bot. Status: {(int)response.StatusCode} - {response.ReasonPhrase}. Detalhes: {errorText}");
            }

            using var stream = await response.Content.ReadAsStreamAsync(cancellationToken);
            using var doc = await JsonDocument.ParseAsync(stream, cancellationToken: cancellationToken);

            var root = doc.RootElement;

            if (root.TryGetProperty("answers", out var answersElement) &&
                answersElement.ValueKind == JsonValueKind.Array &&
                answersElement.GetArrayLength() > 0)
            {
                var first = answersElement[0];

                if (first.TryGetProperty("answer", out var answerElement))
                {
                    var answerText = answerElement.GetString();
                    if (!string.IsNullOrWhiteSpace(answerText))
                        return answerText;
                }
            }

            return "Nenhuma resposta relevante foi encontrada na base de conhecimento do Hermes-Bot.";
        }
    }
}
