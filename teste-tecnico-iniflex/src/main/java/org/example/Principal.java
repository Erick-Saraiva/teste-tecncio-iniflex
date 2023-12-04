package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("5000.00"), "Analista"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("6000.50"), "Gerente"));
        funcionarios.add(new Funcionario("Carlos", LocalDate.of(1992, 10, 8), new BigDecimal("4500.75"), "Desenvolvedor"));

        // 3.2 – Remover o funcionário “João” da lista
        funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas as informações
        funcionarios.forEach(funcionario -> System.out.println(funcionario.nome + ", " +
                funcionario.dataNascimento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", " +
                funcionario.getSalario().toString().replace('.', ',') + ", " +
                funcionario.getFuncao()));

        // 3.4 – Os funcionários recebem 10% de aumento de salário
        funcionarios.forEach(funcionario -> funcionario.getSalario().multiply(new BigDecimal("1.1")));

        // 3.5 – Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcionários na função " + funcao + ":");
            lista.forEach(f -> System.out.println(f.nome));
            System.out.println();
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        funcionarios.stream()
                .filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 || funcionario.dataNascimento.getMonthValue() == 12)
                .forEach(funcionario -> System.out.println(funcionario.nome + " faz aniversário no mês " + funcionario.dataNascimento.getMonthValue()));

        // 3.9 – Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(f -> f.dataNascimento));
        int idade = LocalDate.now().getYear() - funcionarioMaisVelho.dataNascimento.getYear();
        System.out.println("O funcionário mais velho é " + funcionarioMaisVelho.nome + " com " + idade + " anos.");

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(funcionario -> funcionario.nome))
                .collect(Collectors.toList());
        System.out.println("Funcionários em ordem alfabética:");
        funcionariosOrdenados.forEach(f -> System.out.println(f.nome));

        // 3.11 – Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("O total dos salários dos funcionários é: " + totalSalarios.toString().replace('.', ','));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario ->
                System.out.println(funcionario.nome + " ganha " + funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP) + " salários mínimos."));
    }
}
