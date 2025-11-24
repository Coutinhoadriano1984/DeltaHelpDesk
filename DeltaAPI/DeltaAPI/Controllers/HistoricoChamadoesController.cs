using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DeltaAPI.Data;
using DeltaAPI.Models;

namespace DeltaAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class HistoricoChamadoesController : ControllerBase
    {
        private readonly DeltaContext _context;

        public HistoricoChamadoesController(DeltaContext context)
        {
            _context = context;
        }

        // GET: api/HistoricoChamadoes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<HistoricoChamado>>> GetHistoricoChamados()
        {
            return await _context.HistoricoChamados.ToListAsync();
        }

        // GET: api/HistoricoChamadoes/5
        [HttpGet("{id}")]
        public async Task<ActionResult<HistoricoChamado>> GetHistoricoChamado(int id)
        {
            var historicoChamado = await _context.HistoricoChamados.FindAsync(id);

            if (historicoChamado == null)
            {
                return NotFound();
            }

            return historicoChamado;
        }

        // PUT: api/HistoricoChamadoes/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutHistoricoChamado(int id, HistoricoChamado historicoChamado)
        {
            if (id != historicoChamado.HistoricoId)
            {
                return BadRequest();
            }

            _context.Entry(historicoChamado).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!HistoricoChamadoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/HistoricoChamadoes
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<HistoricoChamado>> PostHistoricoChamado(HistoricoChamado historicoChamado)
        {
            _context.HistoricoChamados.Add(historicoChamado);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetHistoricoChamado", new { id = historicoChamado.HistoricoId }, historicoChamado);
        }

        // DELETE: api/HistoricoChamadoes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteHistoricoChamado(int id)
        {
            var historicoChamado = await _context.HistoricoChamados.FindAsync(id);
            if (historicoChamado == null)
            {
                return NotFound();
            }

            _context.HistoricoChamados.Remove(historicoChamado);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool HistoricoChamadoExists(int id)
        {
            return _context.HistoricoChamados.Any(e => e.HistoricoId == id);
        }
    }
}
