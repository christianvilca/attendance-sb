package org.parish.attendancesb.services.carnet;

import org.parish.attendancesb.services.interfaces.ReceiverPersonService;
import org.parish.attendancesb.services.utils.image.ImageBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@Component
public class CarnetFront {

    int width;
    int height;
//    private FXGraphics2D graphics;
//    private GraphicsContext gc;

    private Graphics2D graphics;
    private BufferedImage bufferedImage;

    @Autowired
    private ReceiverPersonService service;

    public CarnetFront() {

        width = 1006;
        height = 651;

//        width = 500;
//        height = 325;

//        // Crea un canvas con un ancho y alto de 300 píxeles
//        Canvas canvas = new Canvas(500, 325);
//
//        // Obtiene una referencia al contexto gráfico del canvas
//        gc = canvas.getGraphicsContext2D();
//
//
//        // Crea una instancia de FXGraphics2D a partir del contexto gráfico del canvas
//        graphics = new FXGraphics2D(gc);
//

        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        graphics = bufferedImage.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private int proportionX(float percentage) {
        return Math.round(width * percentage / 100);
    }

    private int proportionY(float percentage) {
        return Math.round(height * percentage / 100);
    }

    public void drawToFile() {
        graphics.dispose();
        RenderedImage rendImage = bufferedImage;

        File file = new File("carnet_front.png");
        try {
            ImageIO.write(rendImage, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawMainRectangle() {
        // Establece el color para las siguientes operaciones de dibujo
        graphics.setColor(Color.WHITE);

        // Dibuja un rectángulo en la posición (10, 10) con un ancho y alto de 280 píxeles
        graphics.fillRect(0, 0, width, height);
    }

    public void drawTitleRectangle() {
        Color color = new Color(0, 150, 65);
        GradientPaint gradient = new GradientPaint(0, 0, Color.WHITE, width / 2, 0, color);
        graphics.setPaint(gradient);
        graphics.fillRect(0, proportionY(4.3f), width, proportionY(11.98f));
    }

    public void drawBottomPolygon1() {
//        Color color = new Color(0, 150, 65);
        Color color = new Color(0, 150, 65, 128);
        graphics.setColor(color);
        int[] x = {
                0,
                proportionX(24.35f),
                proportionX(25.84f),
                width,
                width,
                0
        };
        int[] y = {
                proportionY(82.49f),
                proportionY(82.49f),
                proportionY(80.03f),
                proportionY(80.03f),
                height,
                height
        };
        graphics.fillPolygon(x, y, 6);
//        graphics.fillRect(0, proportionY(82.49f), width, height - proportionY(82.49f));
    }

    public void drawBottomPolygon2() {
        Color color = new Color(0, 150, 65);
        graphics.setColor(color);
        int[] x = {
                0,
                proportionX(24.35f),
                proportionX(25.84f),
                width,
                width,
                0
        };
        int[] y = {
                proportionY(84.49f),
                proportionY(84.49f),
                proportionY(82.49f),
                proportionY(82.49f),
                height,
                height
        };
        graphics.fillPolygon(x, y, 6);
    }

    public void drawImage() {
        BufferedImage image = ImageBase64.decodeToBufferImage(service.getById(48).getPhoto());
//        BufferedImage image = ImageBase64.decodeBuffer("");
        graphics.drawImage(image, proportionX(4.77f), proportionY(7.37f), proportionX(36.68f), proportionY(72.2f), null);
//        graphics.drawImage(image, proportionX(4.77f), proportionY(7.37f), null);
    }

    public void drawTextPrueba1() {
//        graphics.setColor(Color.WHITE);
//        graphics.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, Color.WHITE));
//        graphics.setColor(Color.BLACK);
//        graphics.setFont(new Font("Arial", Font.PLAIN, 20));
//        graphics.drawString("San Antonio Abad", proportionX(61.63f), proportionY(9.37f));

        ///////////////////////////
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//
//        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
//                RenderingHints.VALUE_RENDER_QUALITY);

//        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        ///////////////////////////

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4.4f)));
//        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(4));
//        graphics.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.drawString("San Antonio 1", proportionX(61.63f), proportionY(10f));

        ///////////////////////////

        TextLayout tl = new TextLayout(
                "San Antonio 2",
                graphics.getFont(),
                graphics.getFontRenderContext()//frc//
        );
        tl.draw(graphics, proportionX(61.63f), proportionY(22f));

        Shape shape = tl.getOutline(null);

        graphics.translate(proportionX(61.63f), proportionY(15.36f));
//        graphics.setStroke(new BasicStroke((float) 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setColor(Color.RED);
        graphics.draw(shape);
        graphics.setColor(Color.BLACK);
        graphics.fill(shape);
        ///////////////////////////

        Font f = new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4.4f));
        TextLayout tl1 = new TextLayout(
                "San Antonio 3",
                f,
                graphics.getFontRenderContext());
        tl1.draw(graphics, proportionX(61.63f), proportionY(30f));
        Shape sha = tl1.getOutline(null);
        graphics.translate(proportionX(61.63f), proportionY(45.36f));
        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(4f));
        graphics.draw(sha);
        graphics.setColor(Color.BLACK);
        graphics.fill(sha);
        //graphics.translate(proportionX(61.63f), proportionY(30.36f));

    }

    public void drawTextPrueba2() {
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        graphics.translate(proportionX(61.63f), proportionY(50.36f));
        Graphics2D g2 = graphics;
        int w = 500;
        int h = 200;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setFont(new Font("sansserif", Font.PLAIN, w / 8));
        FontRenderContext frc = g2.getFontRenderContext();
//        Font f = new Font("sansserif", Font.PLAIN, w / 8);
//        Font f1 = new Font("sansserif", Font.ITALIC, w / 8);
//        String s = "AttributedString";
//        AttributedString as = new AttributedString(s);
//        as.addAttribute(TextAttribute.FONT, f, 0, 10);
//        as.addAttribute(TextAttribute.FONT, f1, 10, s.length());
//        AttributedCharacterIterator aci = as.getIterator();

        TextLayout tl = new TextLayout("AttributedString", g2.getFont(), frc);
//        float sw = (float) tl.getBounds().getWidth();
//        float sh = (float) tl.getBounds().getHeight();
//        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w / 2 - sw
//                / 2, h * 0.2 + sh / 2));

        Shape sha = tl.getOutline(null);
        tl.draw(g2, 0, 0);
//        g2.setColor(Color.BLUE);
//        g2.setStroke(new BasicStroke(3f));
//        g2.draw(sha);
//        g2.setColor(Color.MAGENTA);
//        g2.fill(sha);
    }

    public void drawTextTitleParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(1.9f)));
        TextLayout layout = new TextLayout("Parroquia", g2d.getFont(), g2d.getFontRenderContext());
        Shape shape = layout.getOutline(AffineTransform.getTranslateInstance(proportionX(61.63f), proportionY(8.5f)));
        g2d.setStroke(new BasicStroke(proportionX(0.5f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    public void drawTextParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout("Inmaculado Corazon", g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(61.63f), proportionY(14.5f));
        at.scale(proportionX(37.18f) / layout.getBounds().getWidth(), 1); //sx:374
        Shape shape = layout.getOutline(at);
        g2d.setStroke(new BasicStroke(proportionX(0.5f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    public void drawTextName() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(10f)));
        TextLayout layout = new TextLayout("Luis Gonzales Rojass", g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
//        at.setToTranslation(proportionX(61.63f), proportionY(86.5f)); // y:563 w:970
        at.setToTranslation(getPositionX(layout.getBounds().getWidth()), proportionY(98f)); // y:563 w:970
        at.scale(getScaleX(layout.getBounds().getWidth()), 1);
        Shape shape = layout.getOutline(at);
        g2d.setStroke(new BasicStroke(proportionX(0.7f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    public double getScaleX(double value) {
        double result = proportionX(96.42f) / value; //970
        if (result > 1) {
            return 1;
        }
        return result;
    }

    public double getPositionX(double value) {
        double result = proportionX(96.42f) / value;
        if (result > 1) {
            return (proportionX(100f) - value) / 2;
        }
        return proportionX(1.79f);
    }

    public double getScaleY(double value) {
        double result = proportionY(60.83f) / value; // y:396
        if (result > 1) {
            return 1;
        }
        return result;
    }

    public double getPositionY(double value) {
        double result = proportionY(60.83f) / value;
        if (result > 1) {
            return ((proportionY(63.75f) - value) / 2) + proportionY(16.59f) + value;
        }
        return proportionY(78.56f);
    }

    public void drawTextCatequesis() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout("Confirmacion 2015", g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(93.84f), getPositionY(layout.getBounds().getWidth())); // y:563 w:970
        at.scale(1, getScaleY(layout.getBounds().getWidth()));
        at.rotate(Math.toRadians(270));
        Shape shape = layout.getOutline(at);
        Color color = new Color(0, 150, 65);
        g2d.setColor(color);
        g2d.fill(shape);
    }

    public void drawTextGroup() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout("Grupo San Juan", g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(97.91f), getPositionY(layout.getBounds().getWidth())); // y:563 w:970
        at.scale(1, getScaleY(layout.getBounds().getWidth()));
//        at.setToTranslation(proportionX(97.91f), proportionY(78.8f));
        at.rotate(Math.toRadians(270));
        Shape shape = layout.getOutline(at);
        Color color = new Color(0, 150, 65, 128);
        g2d.setColor(color);
        g2d.fill(shape);
    }

    public void draw() {
        drawMainRectangle();
        drawTitleRectangle();
        drawBottomPolygon1();
        drawBottomPolygon2();

        drawTextTitleParroquia();
        drawTextParroquia();
        drawTextName();
        drawTextCatequesis();
        drawTextGroup();
        drawToFile();
    }

    public static void main(String[] args) {
        CarnetFront grafics = new CarnetFront();
        grafics.draw();
    }
}
