using System;
using System.Windows.Forms;

namespace AdminDesktop
{
    public partial class frmLogin : Form
    {
        public frmLogin()
        {
            InitializeComponent();
        }

        private void frmLogin_Load(object sender, EventArgs e)
        {
            // Inicialização extra, se necessário
        }

        private void btnEntrar_Click(object sender, EventArgs e)
        {
            if (txtUsuario.Text == "admin" && txtSenha.Text == "1234")
            {
                MessageBox.Show("Bem-vindo!");
                this.Hide();
                // new frmPrincipal().Show(); // tela principal
                 new frmChamadoUsuario().Show(); // tela principal
            }
            else
            {
                MessageBox.Show("Usuário ou senha inválidos.");
                txtSenha.Clear();
                txtUsuario.Focus();
            }
           
        }

        private void btnSair_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
