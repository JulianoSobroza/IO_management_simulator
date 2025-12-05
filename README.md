# Simulador de Gerenciamento de E/S com Interrupções

##  Objetivo
Demonstrar o fluxo de execução de um processo sendo interrompido por dispositivos de hardware (Teclado, Disco, Impressora), o salvamento de contexto, o tratamento da interrupção e a restauração do contexto original.

## Estrutura do Projeto

O código está organizado nos seguintes pacotes:

*   **`devices`**: Contém a lógica dos dispositivos de hardware.
    *   `Device`: Classe abstrata base. Define a lógica de geração aleatória de interrupções.
    *   `Keyboard` (Alta Prioridade), `Printer` (Média Prioridade), `SATA` (Baixa Prioridade): Implementações específicas.
*   **`interrupts`**: Gerenciamento das interrupções.
    *   `Interrupt`: Representa o evento de interrupção.
    *   `InterruptController`: Fila de prioridade que armazena as interrupções pendentes.
*   **`simulation`**: Núcleo da simulação.
    *   `Processo`: Simula um programa em execução com Program Counter (PC).
    *   `Contexto`: Representa o estado salvo do processador (snapshot dos registradores).
    *   `Simulador`: O "loop principal" do SO. Gerencia o tempo, executa o processo e verifica interrupções.
*   **`log`**: Utilitários de saída.
    *   `Logger`: Responsável por imprimir mensagens coloridas no terminal e salvar em arquivo.

## Fluxo de Execução

1.  **Inicialização**: O `Main` instancia o simulador, o processo e registra os dispositivos.
2.  **Loop de Simulação (Clock)**:
    *   A cada ciclo ("tick" do relógio), o **Processo** executa uma instrução, alterando aleatoriamente seus registradores simulados.
    *   O **Simulador** pergunta a cada dispositivo se ele precisa gerar uma interrupção.
3.  **Geração de Interrupção**:
    *   Se um dispositivo gera uma interrupção, ela é enviada ao `InterruptController`.
    *   O evento é logado em **Amarelo**.
4.  **Tratamento de Interrupção**:
    *   O Simulador verifica se há interrupções pendentes.
    *   Se houver, o fluxo normal é pausado.
    *   **Salvamento de Contexto**: O estado atual (PC) é salvo. (Logado em **Ciano**).
    *   **Execução da Rotina**: A CPU "desvia" para tratar o dispositivo. (Logado em **Vermelho**).
    *   **Restauração de Contexto**: O estado antigo é recuperado.
5.  **Retomada**: O processo continua exatamente de onde parou.

## Como Executar

Basta executar o arquivo Main.java a partir da pasta raiz

## Recursos Didáticos

*   **Cores no Terminal**: Facilitam a distinção entre execução normal (Verde), avisos de hardware (Amarelo) e ações do Kernel (Vermelho/Ciano).
*   **Visualização de Registradores**: É possível ver os valores de  `PC` mudando e sendo preservados durante a interrupção.
*   **Delays Artificiais**: A simulação roda em "câmera lenta" para facilitar o acompanhamento visual.
