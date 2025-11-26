using System.Net.Http;
using System.Net.Http.Json;

namespace AdminDesktop
{
    public class HermesBotClient
    {
        private readonly HttpClient _httpClient;

        public HermesBotClient(string apiBaseUrl)
        {
            if (string.IsNullOrWhiteSpace(apiBaseUrl))
                throw new ArgumentException("A URL base da API não pode ser vazia.", nameof(apiBaseUrl));

            _httpClient = new HttpClient
            {
                BaseAddress = new Uri(apiBaseUrl)
            };
        }

        private class BotAskRequest
        {
            public string Question { get; set; } = string.Empty;
        }

        private class BotAskResponse
        {
            public string Answer { get; set; } = string.Empty;
        }

        public async Task<string> PerguntarAoHermesAsync(string pergunta)
        {
            if (string.IsNullOrWhiteSpace(pergunta))
                throw new ArgumentException("A pergunta não pode ser vazia.", nameof(pergunta));

            var request = new BotAskRequest
            {
                Question = pergunta
            };

            using var response = await _httpClient.PostAsJsonAsync("api/bot/ask", request);

            if (!response.IsSuccessStatusCode)
            {
                var errorText = await response.Content.ReadAsStringAsync();
                throw new InvalidOperationException(
                    $"Erro ao chamar Hermes-Bot na API. Status: {(int)response.StatusCode} - {response.ReasonPhrase}. Detalhes: {errorText}");
            }

            var result = await response.Content.ReadFromJsonAsync<BotAskResponse>();

            return result?.Answer ?? "O Hermes-Bot não retornou nenhuma resposta.";
        }
    }
}
