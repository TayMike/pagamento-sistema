package com.pagamento.sistema.controller;

import com.pagamento.sistema.entities.Pagamento;
import com.pagamento.sistema.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService PagamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> encontrarPagamento(@PathVariable String cpf) {
        Optional<Pagamento> Pagamento = PagamentoService.encontrarPagamento(cpf);
        return Pagamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Pagamento>> encontrarPagamentos() {
        List<Pagamento> Pagamentos = PagamentoService.encontrarPagamentos();
        if (Pagamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(Pagamentos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody Pagamento Pagamento) {
        try {
            Pagamento PagamentoCadastrado = PagamentoService.cadastrarPagamento(Pagamento);
            return ResponseEntity.ok(PagamentoCadastrado);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<Pagamento> alterarPagamento(@RequestBody Pagamento Pagamento) {
        try {
            Pagamento PagamentoAlterado = PagamentoService.alterarPagamento(Pagamento);
            return ResponseEntity.ok(PagamentoAlterado);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<Pagamento> deletarPagamento(@PathVariable String cpf) {
        try {
            PagamentoService.deletarPagamento(cpf);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
