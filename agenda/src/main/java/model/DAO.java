package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The senha. */
	private String senha = "1234";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, senha);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		try {
			Connection con = conectar();
			PreparedStatement ps = null;
			ps = con.prepareStatement("insert into contatos(nome,fone,email) values(?, ?, ?)");
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.executeUpdate();
			ps.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Leitura contato.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> leituraContato() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		try {
			ResultSet rs = null;
			Connection con = conectar();
			PreparedStatement ps = null;
			ps = con.prepareStatement("select * from contatos order by nome");
			rs = ps.executeQuery();

			while (rs.next()) {
				JavaBeans contato = new JavaBeans();
				contato.setIdcon(rs.getString("idcon"));
				contato.setNome(rs.getString("nome"));
				contato.setFone(rs.getString("fone"));
				contato.setEmail(rs.getString("email"));
				contatos.add(contato);
			}
			rs.close();
			ps.close();
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement("select * from contatos where idcon = ?");
			ps.setString(1, contato.getIdcon());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		Connection con = conectar();
		try {
			PreparedStatement ps = con.prepareStatement("update  contatos set nome=?, fone=?, email=? where idcon = ?");
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getIdcon());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		Connection con = conectar();
		try {
			PreparedStatement ps = con.prepareStatement("delete from contatos where idcon = ?");
			ps.setString(1, contato.getIdcon());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
