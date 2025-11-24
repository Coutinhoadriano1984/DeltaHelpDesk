using DeltaWeb;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;

var builder = WebAssemblyHostBuilder.CreateDefault(args);

builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

// URL base da API (ajuste para a porta correta da DeltaAPI no seu ambiente)
var apiBaseUrl = "https://localhost:7025/"; // Exemplo. Ajuste conforme o launchSettings da DeltaAPI.

builder.Services.AddScoped(sp => new HttpClient
{
    BaseAddress = new Uri(apiBaseUrl)
});

await builder.Build().RunAsync();
