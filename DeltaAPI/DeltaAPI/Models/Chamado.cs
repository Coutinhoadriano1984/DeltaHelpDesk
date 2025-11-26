namespace DeltaAPI.Models
{
    public class Chamado
    {
        public int ChamadoId { get; set; }
        public string Titulo { get; set; }
        public string Descricao { get; set; }
        public string Status { get; set; }
        public string Prioridade { get; set; }
        public DateTime DataAbertura { get; set; }
        public DateTime? DataFechamento { get; set; }
        public int? UsuarioSolicitanteId { get; set; }
        public int? TecnicoResponsavelId { get; set; }
        public int? CategoriaId { get; set; }
    }
}
