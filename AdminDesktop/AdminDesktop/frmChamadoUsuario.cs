using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace AdminDesktop
{
    public partial class frmChamadoUsuario : Form
    {
        // Cliente que fala com a API do Hermes-Bot
        private readonly HermesBotClient _botClient;

        public frmChamadoUsuario()
        {
            InitializeComponent();

            // AJUSTE A URL ABAIXO PARA A PORTA CORRETA DA SUA API
            // Exemplo: se no launchSettings.json estiver "https://localhost:7025"
            var apiBaseUrl = "https://localhost:7025/";

            _botClient = new HermesBotClient(apiBaseUrl);

            // Ligando o clique do botão ao método que consulta o Hermes-Bot
            btnConsultar.Click += btnConsultar_Click;
        }

        private void frmChamadoUsuario_Load(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            Close();
        }

        // 👉 ESTE É O MÉTODO QUE CHAMA O HERMES-BOT
        private async void btnConsultar_Click(object sender, EventArgs e)
        {
            try
            {
                // Vamos usar o texto que o usuário digitou como "pergunta"
                var pergunta = txtSolicitacao.Text;

                if (string.IsNullOrWhiteSpace(pergunta))
                {
                    MessageBox.Show(
                        "Digite a descrição do problema em 'Solicitação' antes de consultar o Hermes-Bot.",
                        "Aviso",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Warning);
                    return;
                }

                btnConsultar.Enabled = false;
                var textoOriginalBotao = btnConsultar.Text;
                btnConsultar.Text = "Consultando Hermes-Bot...";

                // Chama a API (DeltaAPI -> HermesBotService -> Azure)
                var resposta = await _botClient.PerguntarAoHermesAsync(pergunta);

                // Mostra a resposta num MessageBox (simples por enquanto)
                MessageBox.Show(
                    resposta,
                    "Resposta do Hermes-Bot",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show(
                    $"Erro ao consultar o Hermes-Bot: {ex.Message}",
                    "Erro",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }
            finally
            {
                btnConsultar.Enabled = true;
                btnConsultar.Text = "Consultar Chamado";
            }
        }
    }
}
