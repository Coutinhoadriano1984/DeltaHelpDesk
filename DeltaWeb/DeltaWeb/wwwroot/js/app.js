window.drawCategoryChart = (labels, data) => {
    console.log('Desenhando gráfico...', labels, data);
    
};
window.gerarPdfSimples = () => {
    // Pega a classe jsPDF da lib UMD
    const { jsPDF } = window.jspdf;

    const doc = new jsPDF();

    // Título
    doc.setFontSize(18);
    doc.text("Relatório Simples - Delta Helpdesk", 10, 20);

    // Subtítulo
    doc.setFontSize(12);
    doc.text("Este é um exemplo de PDF gerado diretamente no navegador.", 10, 30);

    // Conteúdo
    doc.setFontSize(11);
    const texto = [
        "Este PDF foi gerado usando jsPDF via JavaScript,",
        "sem necessidade de biblioteca .NET de PDF no Blazor.",
        "",
     
    ];

    let y = 40;
    texto.forEach(linha => {
        doc.text(linha, 10, y);
        y += 7;
    });

    // Salva o arquivo
    doc.save("Relatorio.pdf");
};
