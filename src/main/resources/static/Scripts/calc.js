import getDados from "./getDados.js";

document.addEventListener("DOMContentLoaded", () => {
  // Água Usada
  const aguaInputs = {
    litros: document.getElementById("aguaUsada"),
    animais: document.getElementById("qtdAnima"),
    adicionais: document.getElementById("litrosAdicionalDia"),
  };
  Object.values(aguaInputs).forEach(input => {
    input.addEventListener("input", () => {
        const litros = parseFloat(aguaInputs.litros.value);
        const animais = parseInt(aguaInputs.animais.value);
        const adicionais = parseFloat(aguaInputs.adicionais.value);
        if (!isNaN(litros) && !isNaN(animais) && !isNaN(adicionais)) {
            getDados(`aguaUsada/calc?litros=${litros}&animais=${animais}&adicionais=${adicionais}`)
                .then(result => displayResult("aguaUsada", result))
                .catch(error => console.error("Erro ao calcular água usada:", error));
        }
    });
  });
  

  // Combustível
  const combustivelInputs = {
      horas: document.getElementById("combustivelHoras"),
      tratores: document.getElementById("combustivelTratores"),
      litros: document.getElementById("combustivelLitros"),
  };
  Object.values(combustivelInputs).forEach(input => {
      input.addEventListener("input", () => {
          const horas = parseFloat(combustivelInputs.horas.value);
          const tratores = parseInt(combustivelInputs.tratores.value);
          const litros = parseFloat(combustivelInputs.litros.value);
          if (!isNaN(horas) && !isNaN(tratores) && !isNaN(litros)) {
              getDados(`combustivel/calc?horas=${horas}&tratores=${tratores}&litros=${litros}`)
                  .then(result => displayResult("combustivel", result))
                  .catch(error => console.error("Erro ao calcular combustível:", error));
          }
      });
  });

  // Cuidado do Solo
  const soloInputs = {
      toneladas: document.getElementById("soloToneladas"),
      anos: document.getElementById("soloAnos"),
      area: document.getElementById("soloArea"),
  };
  Object.values(soloInputs).forEach(input => {
      input.addEventListener("input", () => {
          const toneladas = parseFloat(soloInputs.toneladas.value);
          const anos = parseInt(soloInputs.anos.value);
          const area = parseFloat(soloInputs.area.value);
          if (!isNaN(toneladas) && !isNaN(anos) && !isNaN(area)) {
              getDados(`cuidadoSolo/calc?toneladas=${toneladas}&anos=${anos}&area=${area}`)
                  .then(result => displayResult("solo", result))
                  .catch(error => console.error("Erro ao calcular cuidado do solo:", error));
          }
      });
  });

  // Eletricidade
  const eletricaInput = document.getElementById("eletrica");
  eletricaInput.addEventListener("input", () => {
      const energiaPORKWH = parseFloat(eletricaInput.value);
      if (!isNaN(energiaPORKWH)) {
          getDados(`eletricidade/calc?energiaPORKWH=${energiaPORKWH}`)
              .then(result => displayResult("eletrica", result))
              .catch(error => console.error("Erro ao calcular eletricidade:", error));
      }
  });

  // Gado
  const gadoInputs = {
      peso: document.getElementById("gadoPeso"),
      quantidade: document.getElementById("gadoQuantidade"),
      anos: document.getElementById("gadoAnos"),
  };
  Object.values(gadoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const peso = parseFloat(gadoInputs.peso.value);
          const quantidade = parseInt(gadoInputs.quantidade.value);
          const anos = parseFloat(gadoInputs.anos.value);
          if (!isNaN(peso) && !isNaN(quantidade) && !isNaN(anos)) {
              getDados(`gado/calc?peso=${peso}&quantidade=${quantidade}&anos=${anos}`)
                  .then(result => displayResult("gado", result))
                  .catch(error => console.error("Erro ao calcular gado:", error));
          }
      });
  });

  // Mão de Obra
  const maoObraInputs = {
      pessoas: document.getElementById("maoObraPessoas"),
      horas: document.getElementById("maoObraHoras"),
      dias: document.getElementById("maoObraDias"),
  };
  Object.values(maoObraInputs).forEach(input => {
      input.addEventListener("input", () => {
          const pessoas = parseInt(maoObraInputs.pessoas.value);
          const horas = parseFloat(maoObraInputs.horas.value);
          const dias = parseInt(maoObraInputs.dias.value);
          if (!isNaN(pessoas) && !isNaN(horas) && !isNaN(dias)) {
              getDados(`maoObra/calc?pessoas=${pessoas}&horas=${horas}&dias=${dias}`)
                  .then(result => displayResult("maoObra", result))
                  .catch(error => console.error("Erro ao calcular mão de obra:", error));
          }
      });
  });

  // Maquinários
  const maquinarioInputs = {
      horas: document.getElementById("maquinarioHoras"),
      valor: document.getElementById("maquinarioValor"),
  };
  Object.values(maquinarioInputs).forEach(input => {
      input.addEventListener("input", () => {
          const horas = parseFloat(maquinarioInputs.horas.value);
          const valor = parseFloat(maquinarioInputs.valor.value);
          if (!isNaN(horas) && !isNaN(valor)) {
              getDados(`maquinarios/calc?horas=${horas}&valor=${valor}`)
                  .then(result => displayResult("maquinario", result))
                  .catch(error => console.error("Erro ao calcular maquinários:", error));
          }
      });
  });

  // Perda do Solo
  const perdaSoloInput = document.getElementById("perdaSolo");
  perdaSoloInput.addEventListener("input", () => {
      const perda = parseFloat(perdaSoloInput.value);
      if (!isNaN(perda)) {
          getDados(`perdaSolo/calc?perda=${perda}`)
              .then(result => displayResult("perdaSolo", result))
              .catch(error => console.error("Erro ao calcular perda do solo:", error));
      }
  });

  // Potencial Químico
  const potencialQuimicoInputs = {
      area: document.getElementById("potencialQuimicoArea"),
      chuva: document.getElementById("potencialQuimicoChuva"),
  };
  Object.values(potencialQuimicoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const area = parseFloat(potencialQuimicoInputs.area.value);
          const chuva = parseFloat(potencialQuimicoInputs.chuva.value);
          if (!isNaN(area) && !isNaN(chuva)) {
              getDados(`potencialQuimico/calc?area=${area}&chuva=${chuva}`)
                  .then(result => displayResult("potencialQuimico", result))
                  .catch(error => console.error("Erro ao calcular potencial químico:", error));
          }
      });
  });

  // Produção de Leite
  const producaoLeiteInputs = {
      area: document.getElementById("producaoLeiteArea"),
      litros: document.getElementById("producaoLeiteLitros"),
  };
  Object.values(producaoLeiteInputs).forEach(input => {
      input.addEventListener("input", () => {
          const area = parseFloat(producaoLeiteInputs.area.value);
          const litros = parseFloat(producaoLeiteInputs.litros.value);
          if (!isNaN(area) && !isNaN(litros)) {
              getDados(`producaoLeite/calc?area=${area}&litros=${litros}`)
                  .then(result => displayResult("producaoLeite", result))
                  .catch(error => console.error("Erro ao calcular produção de leite:", error));
          }
      });
  });
});

  // Ração
  const racaoInputs = {
      sacas: document.getElementById("racaoSacas"),
      valor: document.getElementById("racaoValor"),
  };
  Object.values(racaoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const sacas = parseInt(racaoInputs.sacas.value);
          const valor = parseFloat(racaoInputs.valor.value);
          if (!isNaN(sacas) && !isNaN(valor)) {
              getDados(`racao/calc?sacas=${sacas}&valor=${valor}`)
                  .then(result => displayResult("racao", result))
                  .catch(error => console.error("Erro ao calcular ração:", error));
          }
      });
  });


  // Consumo e Manutenção
  const consumoInputs = {
      valor: document.getElementById("consumoFazendaValor"),
      anos: document.getElementById("consumoFazendaAnos"),
  };
  Object.values(consumoInputs).forEach(input => {
      input.addEventListener("input", () => {
          const valor = parseFloat(consumoInputs.valor.value);
          const anos = parseInt(consumoInputs.anos.value);
          if (!isNaN(valor) && !isNaN(anos)) {
              getDados(`consumoManutencao/calc?valor=${valor}&anos=${anos}`)
                  .then(result => displayResult("consumoFazenda", result))
                  .catch(error => console.error("Erro ao calcular consumo e manutenção:", error));
          }
      });
  });

  
// Função para exibir o resultado abaixo do campo correspondente
function displayResult(fieldId, result) {
  let resultElement = document.getElementById(`${fieldId}-result`);
  if (!resultElement) {
      const inputElement = document.getElementById(fieldId);
      resultElement = document.createElement("div");
      resultElement.id = `${fieldId}-result`;
      resultElement.style.marginTop = "10px";
      inputElement.parentElement.appendChild(resultElement);
  }
  resultElement.textContent = `Resultado: ${result.toFixed(2)}`;
}
