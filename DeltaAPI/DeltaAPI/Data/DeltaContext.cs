using Microsoft.EntityFrameworkCore;
using DeltaAPI.Models; // Supondo que suas models estão na pasta Models

namespace DeltaAPI.Data
{
    public class DeltaContext : DbContext
    {
        public DeltaContext(DbContextOptions<DeltaContext> options) : base(options) { }

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Categoria> Categorias { get; set; }
        public DbSet<Chamado> Chamados { get; set; }
        public DbSet<HistoricoChamado> HistoricoChamados { get; set; }
        public DbSet<FAQ> FAQs { get; set; }
    }
}
