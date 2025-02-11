import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImgService {
    public static JLabel carregarImagem(String filePath) {
        BufferedImage imagem;
        JLabel imagemContainer;
        try {
            InputStream input = ImgService.class.getResourceAsStream(filePath);
            imagem = ImageIO.read(input);
            imagemContainer = new JLabel(new ImageIcon(imagem));
            return imagemContainer;
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static void atualizarImagem(JLabel imagemContainer, String filePath) {
        BufferedImage imagem;
        try {
            InputStream input = ImgService.class.getResourceAsStream(filePath);
            imagem = ImageIO.read(input);
            imagemContainer.setIcon(new ImageIcon(imagem));
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
