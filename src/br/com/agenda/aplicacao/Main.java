package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		/*Contato contato = new Contato();
		contato.setNome("Carlos Maia");
		contato.setIdade(35);
		contato.setDataCadastro(new Date());*/
		
		//contatoDao.save(contato);
		Contato c2 = new Contato();
		c2.setNome("Amara");
		c2.setIdade(62);
		c2.setDataCadastro(new Date());
		c2.setId(3);
		
	
		
		contatoDao.deleteById(12);
		
		
		for(Contato c : ContatoDAO.getContatos()) {
			System.out.println("Contato: " + c.getNome());
			System.out.println("Idade: " + c.getIdade());
		}
	}
}

