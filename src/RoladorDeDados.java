import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RoladorDeDados extends JFrame {
    public RoladorDeDados() {
        super("Rolador de Dados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        adicionarComponentes();
    }

    private void adicionarComponentes() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel bannerImg = ImgService.carregarImagem("recursos/banner.png");
        bannerImg.setBounds(45, 25, 600, 100);
        painel.add(bannerImg);

        JLabel dadoUmImg = ImgService.carregarImagem("recursos/dice1.png");
        dadoUmImg.setBounds(100, 200, 200, 200);
        painel.add(dadoUmImg);

        JLabel dadoDoisImg = ImgService.carregarImagem("recursos/dice1.png");
        dadoDoisImg.setBounds(390, 200, 200, 200);
        painel.add(dadoDoisImg);

        Random rand = new Random();
        JButton rolarDados = new JButton("Rolar Dados");
        rolarDados.setBounds(250, 550, 200, 50);
        rolarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolarDados.setEnabled(false);

                long tempoInicial = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long tempoFinal = System.currentTimeMillis();
                        try {
                            while((tempoFinal-tempoInicial)/1000F < 3) {
                                int dadoUm = rand.nextInt(1, 7);
                                int dadoDois = rand.nextInt(1, 7);

                                ImgService.atualizarImagem(dadoUmImg, "recursos/dice"+dadoUm+".png");
                                ImgService.atualizarImagem(dadoDoisImg, "recursos/dice"+dadoDois+".png");

                                repaint();
                                revalidate();

                                Thread.sleep(60);
                                tempoFinal = System.currentTimeMillis();
                            }
                            rolarDados.setEnabled(true);
                        } catch (InterruptedException e) {
                            System.out.println("Error na Thread: " + e);
                        }
                    }
                });
                rollThread.start();
            }
        });
        painel.add(rolarDados);

        this.setContentPane(painel);

    }
}
