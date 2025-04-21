package br.com.example.emergia.main;

import br.com.example.emergia.EmergiaApplication;
import br.com.example.emergia.model.*;
import org.springframework.boot.SpringApplication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner leitura =new Scanner(System.in);
        double valorEmergeticoTotal = 0;
        int opcao;

        while (true) {
            System.out.println("""
                    **************************************************************************
                    Bem vindo ao calculador de emergia para agropecuária! Escolha um cálculo:
                     1 - Calcular emergia por agua usada
                     2 - Calcular emergia por combustivel utilizado
                     3 - Calcular emergia por energia elétrica
                     4 - Calcular emergia por mão de obra
                     5 - Calcular emergia por quantidade de ração
                     6 - Calcular emergia por cuidado do solo
                     7 - Calcular emergia por maquinário
                     8 - Calcular emergia por perda do solo
                     9 - Calcular emergia por potencial químico
                    10 - Calcular emergia por Valor de consumo e manutenção da fazenda
                    11 - Calcular emergia do gado
                    12 - Calcular emergia por produção de leite
                    
                
                    99 - Verificar valor emergético final
                    0 - Sair
                    **************************************************************************""");
            System.out.println("Opção desejada: ");
            opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a quantidade de litros por dia por animal:");
                    double litroPorDiaAnimal = leitura.nextDouble();
                    System.out.println("Digite a quantidade de animais:");
                    int qtdAnimal = leitura.nextInt();
                    System.out.println("Digite a quantidade de litros adicionais por dia:");
                    double litrosAdicionalDia = leitura.nextInt();
                    AguaUsada aguaUsada = new AguaUsada(litroPorDiaAnimal, qtdAnimal, litrosAdicionalDia);
                    valorEmergeticoTotal += aguaUsada.calcAU();
                    System.out.println("Valor emergético da água usada: " + aguaUsada.calcAU());
                    break;
                case 2:
                    System.out.println("Digite a quantidade de horas de tratores por ano:");
                    double combustivel = leitura.nextDouble();
                    System.out.println("Digite quantos tratores são utilizados:");
                    double qtdTrator = leitura.nextDouble();
                    System.out.println("Digite quantos litros de combustível é gasto por hora:");
                    double valorCombustivel = leitura.nextDouble();
                    CombustivelUsado combustivelUtilizado = new CombustivelUsado(combustivel,
                            valorCombustivel, qtdTrator);
                    valorEmergeticoTotal += combustivelUtilizado.calcularCombustivelUsado();
                    System.out.println("Valor emergético do combustivel: "
                            + combustivelUtilizado.calcularCombustivelUsado());
                    break;
                case 3:
                    System.out.println("Digite a quantidade de energia elétrica utilizada por KWH:");
                    double energiaEletrica = leitura.nextDouble();
                    Eletricidade energia = new Eletricidade(energiaEletrica);
                    valorEmergeticoTotal += energia.calcE();
                    System.out.println("Valor emergético da energia elétrica: " + energia.calcE());
                    break;
                case 4:
                    System.out.println("Digite a quantidade de pessoas:");
                    int pessoa = leitura.nextInt();
                    System.out.println("Digite a quantidade de horas trabalhadas:");
                    double horasTrabalhada = leitura.nextInt();
                    System.out.println("Digite a quantidade de dias trabalhados:");
                    int qtdDiasTrabalhado = leitura.nextInt();
                    MaoObra maoObra = new MaoObra(pessoa, horasTrabalhada, qtdDiasTrabalhado);
                    valorEmergeticoTotal += maoObra.calcMO();
                    System.out.println("Valor emergético da mão de obra: " + maoObra.calcMO());
                    break;
                case 5:
                    System.out.println("Digite a quantidade de ração utilizada em sacas:");
                    int saca = leitura.nextInt();
                    System.out.println("Digite o valor da saca:");
                    double valorSaca = leitura.nextDouble();
                    System.out.println("Analisando...");
                    Racao racaoUsada = new Racao(saca, valorSaca);
                    valorEmergeticoTotal += racaoUsada.calcR();
                    System.out.println("Valor emergético da ração: " + racaoUsada.calcR());
                    break;
                case 6:
                    System.out.println("Digite a quantidade de tonelada de produto utilizado para cuidar: ");
                    double toneladasPorHa7Anos = leitura.nextDouble();
                    System.out.println("Digite a quantidade de anos esas toneladas foi usada: ");
                    int ano = leitura.nextInt();
                    System.out.println("Digite a quantidade de area devastada pelo gado em Hectares:");
                    double areaDevastadaPeloGado = leitura.nextDouble();
                    CuidadoSolo cauidadoSolo = new CuidadoSolo(toneladasPorHa7Anos,areaDevastadaPeloGado, ano);
                    valorEmergeticoTotal += cauidadoSolo.calcCS();
                    System.out.println("Valor emergético do cuidado do solo: " + cauidadoSolo.calcCS());
                    break;
                case 7:
                    System.out.println("Digite a quantidade de horas que o trator é usado:");
                    double qtdHoraTrator = leitura.nextDouble();
                    System.out.println("Digite o valor da hora do Trator, em reais: ");
                    double valorHoraTrator = leitura.nextDouble();
                System.out.println("Analisando...");
                    Maquinarios maquinarios = new Maquinarios(qtdHoraTrator,valorHoraTrator);
                    valorEmergeticoTotal += maquinarios.calcM();
                    System.out.println("Valor emergético de manutenção: " + maquinarios.calcM());
                    break;
                case 8:
                    System.out.println("Digite a quantidade de perda do solo:");
                    double perdaSolo = leitura.nextDouble();
                    PerdaSolo perdasolo = new PerdaSolo(perdaSolo);
                    valorEmergeticoTotal += perdasolo.calcPS();
                    System.out.println("Valor emergético da perda do solo: " + perdasolo.calcPS());
                    break;
                case 9:
                    System.out.println("Digite a area da fazenda em HA(hectares):");
                    double haFazenda = leitura.nextDouble();
                    System.out.println("Digite a quantidade de chuva por ano em m3 ");
                    double precipitacao = leitura.nextDouble();
                    PotencialQuimico pontecialquimico = new PotencialQuimico(haFazenda, precipitacao);
                    valorEmergeticoTotal += pontecialquimico.calcPQ();
                    System.out.println("Valor emergético do potencial químico da agua: " + pontecialquimico.calcPQ());
                    break;
                case 10:
                    System.out.println("Digite o valor monetário utilizado para o consumo e manutenção do gado e fazenda por ano: ");
                    double bens = leitura.nextDouble();
                    System.out.println("Digite a quantidade de anos deste gasto: ");
                    int anos = leitura.nextInt();
                    System.out.println("Analisando...");
                    ValorConsumoManutencao valorConsumoManutencao = new ValorConsumoManutencao(bens,anos);
                    valorEmergeticoTotal += valorConsumoManutencao.calcBens();
                    System.out.println("Valor emergético do maquinário: " + valorConsumoManutencao.calcBens());
                    break;
                case 11:
                    System.out.println("Digite o peso médio do gado em kg: ");
                    double pesoKgMedia = leitura.nextDouble();
                    System.out.println("Digite a quantidade de animais: ");
                    int numeroAnimais = leitura.nextInt();
                    System.out.println("Digite a quantidade de anos de vida média do gado: ");
                    float anosVidamedia = leitura.nextFloat();
                    Gado gado = new Gado(pesoKgMedia, numeroAnimais, anosVidamedia);
                    valorEmergeticoTotal += gado.calcGado();
                    System.out.println("Valor emergético do gado: " + gado.calcGado());
                    break;
                case 12:
                    System.out.println("Digite a area da sua fazenda: ");
                    double HaFazenda = leitura.nextDouble();
                    System.out.println("Digite a quantidade de leite produzido por dia: ");
                    double leiteDia = leitura.nextDouble();
                    ProducaoLeite producaoLeite = new ProducaoLeite(leiteDia,HaFazenda);
                    valorEmergeticoTotal += producaoLeite.calcPL();
                    System.out.println("Valor emergético do maquinário: " + producaoLeite.calcPL());
                    break;
                case 99:
                    System.out.println("Valor emergético total: " + valorEmergeticoTotal);
                    break;
                case 0:
                    System.out.println("Saindo do programa e desativando Spring...");
                    leitura.close();
                    SpringApplication.exit(EmergiaApplication.getContext());
                    System.exit(0);
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }

            System.out.println("Deseja fazer mais cálculos? (1 - Sim, 0 - Não)");
            int continuar = leitura.nextInt();
            if (continuar == 0) {
                System.out.println("Valor emergético total: " + valorEmergeticoTotal);
                break;
            }
        }
    }

}