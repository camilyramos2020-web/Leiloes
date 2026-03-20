/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
     conn = new conectaDAO().connectDB();
     String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        
        
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    } catch (SQLException erro) {
     
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
    } 
        
     }
    
   
    public ArrayList<ProdutosDTO> listarProdutos(){
       conn = new conectaDAO().connectDB();
       String sql = "SELECT * FROM produtos";
   
       return listagem;
    }
    
    public void venderProduto(int id) {
    conn = new conectaDAO().connectDB();
    String sql = "UPDATE produtos SET status = ? WHERE id = ?";
    
    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, "Vendido");
        prep.setInt(2, id);
        
        prep.executeUpdate();
    } catch (Exception e) {
        System.out.println("Erro ao vender produto: " + e.getMessage());
    }
}
    
   public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto);
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar vendidos: " + e.getMessage());
    }
    return listagem;
}     
}

