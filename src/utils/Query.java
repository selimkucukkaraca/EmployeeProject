package utils;

public interface Query {
    String findAllQuery = "Select * from ";
    String deleteById = "Delete from calisanlar where id = ?";
    String save = "Insert Into calisanlar (ad,soyad,departman,maas) VALUES(?,?,?,?)";
    String login = "Select * From adminler where username = ? and password = ?";
}
