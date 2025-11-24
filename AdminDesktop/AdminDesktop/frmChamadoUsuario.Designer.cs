namespace AdminDesktop
{
    partial class frmChamadoUsuario
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            btnSair = new Button();
            btnConsultar = new Button();
            pnlFqa = new Panel();
            lblFaq = new Label();
            btnAbrirChamado = new Button();
            btnOk = new Button();
            groupBox1 = new GroupBox();
            txtResumo = new TextBox();
            txtUsuario = new TextBox();
            txtSolicitacao = new TextBox();
            lblUsuario = new Label();
            lblGrupo = new Label();
            comBoxGrupo = new ComboBox();
            txtEmail = new TextBox();
            lblResumo = new Label();
            lblAssunto = new Label();
            lblEmail = new Label();
            comBoxAssunto = new ComboBox();
            lblSolicitacao = new Label();
            btnSalvar = new Button();
            label8 = new Label();
            btnLimpar = new Button();
            pnlFqa.SuspendLayout();
            groupBox1.SuspendLayout();
            SuspendLayout();
            // 
            // btnSair
            // 
            btnSair.Font = new Font("Segoe UI", 9.75F, FontStyle.Bold);
            btnSair.Location = new Point(1101, 526);
            btnSair.Name = "btnSair";
            btnSair.Size = new Size(145, 38);
            btnSair.TabIndex = 18;
            btnSair.Text = "Sair";
            btnSair.UseVisualStyleBackColor = true;
            btnSair.Click += btnSair_Click;
            // 
            // btnConsultar
            // 
            btnConsultar.Font = new Font("Segoe UI", 9.75F, FontStyle.Bold);
            btnConsultar.Location = new Point(920, 526);
            btnConsultar.Name = "btnConsultar";
            btnConsultar.Size = new Size(145, 38);
            btnConsultar.TabIndex = 19;
            btnConsultar.Text = "Consultar Chamado";
            btnConsultar.UseVisualStyleBackColor = true;
            // 
            // pnlFqa
            // 
            pnlFqa.Controls.Add(lblFaq);
            pnlFqa.Controls.Add(btnAbrirChamado);
            pnlFqa.Controls.Add(btnOk);
            pnlFqa.Location = new Point(888, 68);
            pnlFqa.Name = "pnlFqa";
            pnlFqa.Size = new Size(358, 427);
            pnlFqa.TabIndex = 18;
            // 
            // lblFaq
            // 
            lblFaq.AutoSize = true;
            lblFaq.Font = new Font("Segoe UI", 14.25F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblFaq.Location = new Point(162, 28);
            lblFaq.Name = "lblFaq";
            lblFaq.Size = new Size(48, 25);
            lblFaq.TabIndex = 2;
            lblFaq.Text = "FAQ";
            // 
            // btnAbrirChamado
            // 
            btnAbrirChamado.Font = new Font("Segoe UI", 9F, FontStyle.Bold);
            btnAbrirChamado.Location = new Point(202, 371);
            btnAbrirChamado.Name = "btnAbrirChamado";
            btnAbrirChamado.Size = new Size(119, 29);
            btnAbrirChamado.TabIndex = 1;
            btnAbrirChamado.Text = "Abrir Chamado";
            btnAbrirChamado.UseVisualStyleBackColor = true;
            // 
            // btnOk
            // 
            btnOk.Font = new Font("Segoe UI", 9F, FontStyle.Bold);
            btnOk.Location = new Point(32, 372);
            btnOk.Name = "btnOk";
            btnOk.Size = new Size(119, 32);
            btnOk.TabIndex = 0;
            btnOk.Text = "Solucionado";
            btnOk.UseVisualStyleBackColor = true;
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(btnLimpar);
            groupBox1.Controls.Add(label8);
            groupBox1.Controls.Add(btnSalvar);
            groupBox1.Controls.Add(lblSolicitacao);
            groupBox1.Controls.Add(comBoxAssunto);
            groupBox1.Controls.Add(lblEmail);
            groupBox1.Controls.Add(lblAssunto);
            groupBox1.Controls.Add(lblResumo);
            groupBox1.Controls.Add(txtEmail);
            groupBox1.Controls.Add(comBoxGrupo);
            groupBox1.Controls.Add(lblGrupo);
            groupBox1.Controls.Add(lblUsuario);
            groupBox1.Controls.Add(txtSolicitacao);
            groupBox1.Controls.Add(txtUsuario);
            groupBox1.Controls.Add(txtResumo);
            groupBox1.Location = new Point(29, 68);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(824, 427);
            groupBox1.TabIndex = 21;
            groupBox1.TabStop = false;
            groupBox1.Enter += groupBox1_Enter;
            // 
            // txtResumo
            // 
            txtResumo.Font = new Font("Segoe UI", 9.75F);
            txtResumo.Location = new Point(96, 182);
            txtResumo.Name = "txtResumo";
            txtResumo.Size = new Size(262, 25);
            txtResumo.TabIndex = 8;
            // 
            // txtUsuario
            // 
            txtUsuario.Font = new Font("Segoe UI", 9.75F);
            txtUsuario.Location = new Point(96, 111);
            txtUsuario.Name = "txtUsuario";
            txtUsuario.Size = new Size(262, 25);
            txtUsuario.TabIndex = 5;
            // 
            // txtSolicitacao
            // 
            txtSolicitacao.Font = new Font("Segoe UI", 9.75F);
            txtSolicitacao.Location = new Point(96, 220);
            txtSolicitacao.Multiline = true;
            txtSolicitacao.Name = "txtSolicitacao";
            txtSolicitacao.Size = new Size(682, 112);
            txtSolicitacao.TabIndex = 9;
            // 
            // lblUsuario
            // 
            lblUsuario.AutoSize = true;
            lblUsuario.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold);
            lblUsuario.Location = new Point(16, 114);
            lblUsuario.Name = "lblUsuario";
            lblUsuario.Size = new Size(57, 17);
            lblUsuario.TabIndex = 0;
            lblUsuario.Text = "Usuário:";
            // 
            // lblGrupo
            // 
            lblGrupo.AutoSize = true;
            lblGrupo.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold);
            lblGrupo.Location = new Point(16, 149);
            lblGrupo.Name = "lblGrupo";
            lblGrupo.Size = new Size(49, 17);
            lblGrupo.TabIndex = 2;
            lblGrupo.Text = "Grupo:";
            // 
            // comBoxGrupo
            // 
            comBoxGrupo.Font = new Font("Segoe UI", 9.75F);
            comBoxGrupo.FormattingEnabled = true;
            comBoxGrupo.Location = new Point(96, 146);
            comBoxGrupo.Name = "comBoxGrupo";
            comBoxGrupo.Size = new Size(262, 25);
            comBoxGrupo.TabIndex = 14;
            // 
            // txtEmail
            // 
            txtEmail.Font = new Font("Segoe UI", 9.75F);
            txtEmail.Location = new Point(512, 111);
            txtEmail.Name = "txtEmail";
            txtEmail.Size = new Size(261, 25);
            txtEmail.TabIndex = 10;
            // 
            // lblResumo
            // 
            lblResumo.AutoSize = true;
            lblResumo.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold);
            lblResumo.Location = new Point(16, 185);
            lblResumo.Name = "lblResumo";
            lblResumo.Size = new Size(60, 17);
            lblResumo.TabIndex = 3;
            lblResumo.Text = "Resumo:";
            // 
            // lblAssunto
            // 
            lblAssunto.AutoSize = true;
            lblAssunto.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblAssunto.Location = new Point(445, 180);
            lblAssunto.Name = "lblAssunto";
            lblAssunto.Size = new Size(61, 17);
            lblAssunto.TabIndex = 13;
            lblAssunto.Text = "Assunto:";
            lblAssunto.Click += label7_Click;
            // 
            // lblEmail
            // 
            lblEmail.AutoSize = true;
            lblEmail.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblEmail.Location = new Point(458, 114);
            lblEmail.Name = "lblEmail";
            lblEmail.Size = new Size(48, 17);
            lblEmail.TabIndex = 12;
            lblEmail.Text = "E-mail:";
            // 
            // comBoxAssunto
            // 
            comBoxAssunto.Font = new Font("Segoe UI", 9.75F);
            comBoxAssunto.FormattingEnabled = true;
            comBoxAssunto.Location = new Point(512, 177);
            comBoxAssunto.Name = "comBoxAssunto";
            comBoxAssunto.Size = new Size(261, 25);
            comBoxAssunto.TabIndex = 15;
            // 
            // lblSolicitacao
            // 
            lblSolicitacao.AutoSize = true;
            lblSolicitacao.Font = new Font("Segoe UI Semibold", 9.75F, FontStyle.Bold);
            lblSolicitacao.Location = new Point(16, 223);
            lblSolicitacao.Name = "lblSolicitacao";
            lblSolicitacao.Size = new Size(74, 17);
            lblSolicitacao.TabIndex = 4;
            lblSolicitacao.Text = "Solicitação:";
            lblSolicitacao.Click += label5_Click;
            // 
            // btnSalvar
            // 
            btnSalvar.Font = new Font("Segoe UI", 9.75F, FontStyle.Bold);
            btnSalvar.Location = new Point(530, 371);
            btnSalvar.Name = "btnSalvar";
            btnSalvar.Size = new Size(121, 38);
            btnSalvar.TabIndex = 16;
            btnSalvar.Text = "Salvar";
            btnSalvar.UseVisualStyleBackColor = true;
            // 
            // label8
            // 
            label8.AutoSize = true;
            label8.Font = new Font("Segoe UI", 27.75F, FontStyle.Bold | FontStyle.Underline, GraphicsUnit.Point, 0);
            label8.Location = new Point(239, 28);
            label8.Name = "label8";
            label8.Size = new Size(335, 50);
            label8.TabIndex = 20;
            label8.Text = "NOVO CHAMADO";
            // 
            // btnLimpar
            // 
            btnLimpar.Font = new Font("Segoe UI", 9.75F, FontStyle.Bold);
            btnLimpar.Location = new Point(657, 372);
            btnLimpar.Name = "btnLimpar";
            btnLimpar.Size = new Size(121, 37);
            btnLimpar.TabIndex = 17;
            btnLimpar.Text = "Limpar";
            btnLimpar.UseVisualStyleBackColor = true;
            // 
            // frmChamadoUsuario
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1287, 576);
            Controls.Add(groupBox1);
            Controls.Add(pnlFqa);
            Controls.Add(btnConsultar);
            Controls.Add(btnSair);
            Name = "frmChamadoUsuario";
            Text = "frmChamadoUsuario";
            Load += frmChamadoUsuario_Load;
            pnlFqa.ResumeLayout(false);
            pnlFqa.PerformLayout();
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ResumeLayout(false);
        }

        #endregion
        private Button btnSair;
        private Button btnConsultar;
        private Panel pnlFqa;
        private Button btnAbrirChamado;
        private Button btnOk;
        private Label lblFaq;
        private GroupBox groupBox1;
        private Button btnLimpar;
        private Label label8;
        private Button btnSalvar;
        private Label lblSolicitacao;
        private ComboBox comBoxAssunto;
        private Label lblEmail;
        private Label lblAssunto;
        private Label lblResumo;
        private TextBox txtEmail;
        private ComboBox comBoxGrupo;
        private Label lblGrupo;
        private Label lblUsuario;
        private TextBox txtSolicitacao;
        private TextBox txtUsuario;
        private TextBox txtResumo;
    }
}