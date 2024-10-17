package com.APILISTACOMPRAS.demo.Controllers;

import com.APILISTACOMPRAS.demo.Models.ListaCompras;
import com.APILISTACOMPRAS.demo.Models.RepositoryComp;
import com.APILISTACOMPRAS.demo.Models.RepositoryUsu;
import com.APILISTACOMPRAS.demo.Models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ListadeCompra")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

    @Autowired
    RepositoryComp repositoryComp;

    @Autowired
    RepositoryUsu repositoryUsu;

    @GetMapping("/autenticacao/{usuario}/{senha}")
    public ResponseEntity<String> autenticacao(@PathVariable String usuario, @PathVariable String senha) {
        final List<Usuario> listaUsuarios = repositoryUsu.findAll();

        for (Usuario usuarioAtual : listaUsuarios) {
            if (usuarioAtual.getNome().equals(usuario) && usuarioAtual.getSenha().equals(senha)) {
                return ResponseEntity.ok("Usuário autenticado com sucesso!");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos.");
    }

    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<Usuario> cadastraUsuario(
            @RequestBody Usuario usuario
    ) {
        var usuarioSalvo = repositoryUsu.save(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @PostMapping("/cadastrarItem")
    public ResponseEntity<ListaCompras> insereItens(
            @RequestBody ListaCompras Itens
    ) {
        var ItemSalvo = repositoryComp.save(Itens);
        return ResponseEntity.ok(ItemSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaItens(@PathVariable Long id) {
        boolean ItemExistente = repositoryComp.existsById(id);
        if (!ItemExistente) {
            return ResponseEntity.notFound().build();
        }
        repositoryComp.deleteById(id);
        return ResponseEntity.ok(id + "Não encontrado");
    }

    @GetMapping("/todosItens")
    public List<ListaCompras> getHistoricoCompras(){
        final List<ListaCompras> ListaItens = repositoryComp.findAll();
        return ListaItens;
    }
}
