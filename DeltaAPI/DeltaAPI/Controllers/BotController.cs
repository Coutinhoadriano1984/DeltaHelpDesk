using System.Threading;
using System.Threading.Tasks;
using DeltaAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace DeltaAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BotController : ControllerBase
    {
        private readonly HermesBotService _hermesBotService;

        public BotController(HermesBotService hermesBotService)
        {
            _hermesBotService = hermesBotService;
        }

        public class BotAskRequest
        {
            public string Question { get; set; } = string.Empty;
        }

        public class BotAskResponse
        {
            public string Answer { get; set; } = string.Empty;
        }

        /// <summary>
        /// Faz uma pergunta ao Hermes-Bot, que consulta a base de conhecimento no Azure.
        /// URL: POST /api/bot/ask
        /// Body: { "question": "minha impressora não imprime" }
        /// </summary>
        [HttpPost("ask")]
        public async Task<ActionResult<BotAskResponse>> Ask([FromBody] BotAskRequest request, CancellationToken cancellationToken)
        {
            if (request == null || string.IsNullOrWhiteSpace(request.Question))
            {
                return BadRequest("A pergunta não pode ser vazia.");
            }

            var answer = await _hermesBotService.PerguntarAoHermesAsync(request.Question, cancellationToken);

            var response = new BotAskResponse
            {
                Answer = answer
            };

            return Ok(response);
        }
    }
}
