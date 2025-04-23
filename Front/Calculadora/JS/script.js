function calcularEmergia() {
    const eletrica = parseFloat(document.getElementById('eletrica').value) || 0;
    const maoObra = parseFloat(document.getElementById('maoObra').value) || 0;
    const racao = parseFloat(document.getElementById('racao').value) || 0;
    const solo = parseFloat(document.getElementById('solo').value) || 0;
    const maquinario = parseFloat(document.getElementById('maquinario').value) || 0;
    const potencialQuimico = parseFloat(document.getElementById('potencialQuimico').value) || 0;
    const consumoFazenda = parseFloat(document.getElementById('consumoFazenda').value) || 0;
    const gado = parseFloat(document.getElementById('gado').value) || 0;
    const producaoLeite = parseFloat(document.getElementById('producaoLeite').value) || 0;
  
    const uevEletrica = 1.2e5;
    const uevMaoObra = 8e4;
    const uevRacao = 5e4;
    const uevSolo = 3e5;
    const uevMaquinario = 1e5;
    const uevPotencialQuimico = 2e5;
    const uevConsumoFazenda = 2.5e5;
    const uevGado = 3e5;
    const uevProducaoLeite = 1e4;
  
    const emergiaTotal =
      (eletrica * uevEletrica) +
      (maoObra * uevMaoObra) +
      (racao * uevRacao) +
      (solo * uevSolo) +
      (maquinario * uevMaquinario) +
      (potencialQuimico * uevPotencialQuimico) +
      (consumoFazenda * uevConsumoFazenda) +
      (gado * uevGado) +
      (producaoLeite * uevProducaoLeite);
  
    document.getElementById('resultado').innerText =
      `Emergia Total: ${emergiaTotal.toExponential(2)} sej`;
  }
  