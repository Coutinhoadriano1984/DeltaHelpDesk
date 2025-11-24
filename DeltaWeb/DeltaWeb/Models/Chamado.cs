using System;

namespace DeltaWeb.Models
{
    public class Chamado
    {
        public int Id { get; set; }
        public string Assunto { get; set; } = default!;
        public string Status { get; set; } = default!;
        public DateTime DataAbertura { get; set; }
        public string FilaAtendimento { get; set; } = default!;
        public string TecnicoResponsavel { get; set; } = "Não atribuído";
        public string UltimaAtualizacaoTecnica { get; set; } = "Aguardando início do atendimento.";
    }
}