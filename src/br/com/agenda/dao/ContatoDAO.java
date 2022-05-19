package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	//CRUD JDBC
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (? , ? ,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void deleteById(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e){
			e.printStackTrace();
			
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null; 
		
		ResultSet rst = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rst = pstm.executeQuery();
			
			while(rst.next()) {
				
				Contato contato = new Contato();
				
				contato.setId(rst.getInt("id"));
				contato.setNome(rst.getString("nome"));
				contato.setIdade(rst.getInt("idade"));
				contato.setDataCadastro(rst.getDate("datacadastro"));
				
				contatos.add(contato);
				}	
			}catch(Exception e) {
					e.printStackTrace();
				}finally {
				  try {
					if(rst != null) {
						rst.close();
					}
					if(pstm != null) {
						pstm.close();
					}
					if (conn != null) {
						conn.close();
					} 
				}catch(final Exception e) {
						e.printStackTrace();
				}
				return contatos;  
			}
		}
	}
 
