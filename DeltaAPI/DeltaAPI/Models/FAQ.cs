namespace DeltaAPI.Models
{
    public class FAQ
    {
        public int FAQId { get; set; }
        public string Pergunta { get; set; }
        public string Resposta { get; set; }
        public int? CategoriaId { get; set; }
    }
}
