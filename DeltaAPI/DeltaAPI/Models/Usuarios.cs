namespace DeltaAPI.Models
{
    public class Usuario
    {
        public int UsuarioId { get; set; }
        public string Nome { get; set; }
        public string Email { get; set; }
        public string Senha { get; set; }
        public string CPF { get; set; }
        public string Telefone { get; set; }
        public string Perfil { get; set; }
        public DateTime DataCadastro { get; set; }
    }
}
