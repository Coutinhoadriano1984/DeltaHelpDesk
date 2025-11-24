using System.ComponentModel.DataAnnotations;

namespace DeltaAPI.Models
{
    public class HistoricoChamado
    {
        [Key]
        public int HistoricoId { get; set; }    // Chave primária explícita
        public int ChamadoId { get; set; }
        public DateTime DataEvento { get; set; }
        public string DescricaoEvento { get; set; }
        public int? UsuarioId { get; set; }
    }
}
