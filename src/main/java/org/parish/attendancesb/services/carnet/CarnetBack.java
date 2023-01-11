package org.parish.attendancesb.services.carnet;

import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.barcode.Barcode;
import org.parish.attendancesb.services.interfaces.InstitutionService;
import org.parish.attendancesb.utils.image.ImageBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.Normalizer;

@Component
public class CarnetBack {

    private int width;

    private int height;

    private Graphics2D graphics;

    private BufferedImage bufferedImage;

    private ReceiverPerson receiverPerson;

    @Autowired
    private InstitutionService institutionService;

    public void setReceiverPerson(ReceiverPerson receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public CarnetBack() {

    }

    private void initialize() {
        width = 500; // 1006;
        height = 325; // 651;

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

    private void drawMainRectangle() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }

    private void drawBottomPolygon1() {
        Color colorAwt = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        Color color = new Color(colorAwt.getRed(), colorAwt.getGreen(), colorAwt.getBlue(), 128);
        graphics.setColor(color);
        int[] x = {
                0,
                proportionX(48.41f),
                proportionX(51.39f),
                0
        };
        int[] y = {
                proportionY(31.64f),
                proportionY(31.64f),
                proportionY(44.7f),
                proportionY(44.7f)
        };
        graphics.fillPolygon(x, y, 4);
    }

    private void drawBottomPolygon2() {
        Color color = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        graphics.setColor(color);
        int[] x = {
                0,
                proportionX(47.42f),
                proportionX(50.4f),
                0
        };
        int[] y = {
                proportionY(33.64f),
                proportionY(33.64f),
                proportionY(46.54f),
                proportionY(46.54f)
        };
        graphics.fillPolygon(x, y, 4);
    }

    private void drawLogoParroquia() {
        BufferedImage image = ImageBase64.decodeToBufferImage(institutionService.getRegistry().getLogo());
        graphics.drawImage(image, proportionX(2.88f), proportionY(6.45f), proportionX(9.74f), proportionY(12.29f), null);
    }

    private void drawTextTitleParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(1.9f)));
        TextLayout layout = new TextLayout(cleanString(institutionService.getRegistry().getInstitutionName()), g2d.getFont(), g2d.getFontRenderContext());
        Shape shape = layout.getOutline(AffineTransform.getTranslateInstance(proportionX(13.72f), proportionY(11.37f)));
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    private void drawTextParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout(cleanString(institutionService.getRegistry().getName()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(13.72f), proportionY(16.74f));
        at.scale(proportionX(37.18f) / layout.getBounds().getWidth(), 1); //sx:374
        Shape shape = layout.getOutline(at);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    private void drawQr() {
        BufferedImage image = null;
        try {
            image = Barcode.generateQRCodeImage(receiverPerson.getCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        graphics.drawImage(image, proportionX(68.29f), proportionY(10.45f), proportionX(22.86f), proportionY(35.33f), null);
    }

    private void drawTextGroup() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(7f)));
        TextLayout layout = new TextLayout(cleanString(receiverPerson.getGroup().toString()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
//        at.setToTranslation(proportionX(61.63f), proportionY(86.5f)); // y:563 w:970
        at.setToTranslation(getPositionX(layout.getBounds().getWidth()), proportionY(43.85f)); // y:563 w:970
        at.scale(getScaleX(layout.getBounds().getWidth()), 1);
        Shape shape = layout.getOutline(at);
        g2d.setStroke(new BasicStroke(proportionX(0.7f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    private void drawTextName() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(20f)));
        TextLayout layout = new TextLayout(cleanString(receiverPerson.getFirstName()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
//        at.setToTranslation(proportionX(61.63f), proportionY(86.5f)); // y:563 w:970
        at.setToTranslation(getPositionXName(layout.getBounds().getWidth()), proportionY(90.02f)); // y:563 w:970
        at.scale(getScaleXName(layout.getBounds().getWidth()), 1);
        Shape shape = layout.getOutline(at);
        g2d.setStroke(new BasicStroke(proportionX(0.7f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.BLACK);
        g2d.draw(shape);
        Color color = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        g2d.setColor(color);
        g2d.fill(shape);
    }

    private double getScaleXName(double value) {
        double result = proportionX(96.42f) / value; //970
        if (result > 1) {
            return 1;
        }
        return result;
    }

    private double getPositionXName(double value) {
        double result = proportionX(96.42f) / value;
        if (result > 1) {
            return (proportionX(100f) - value) / 2;
        }
        return proportionX(1.79f);
    }

    private double getScaleX(double value) {
        double result = proportionX(96.42f) / value; //970
        if (result > 1) {
            return 1;
        }
        return result;
    }

    private double getPositionX(double value) {
        double result = proportionX(46.62f) / value;
        if (result > 1) {
            return (proportionX(46.62f) - value) / 2;
        }
        return proportionX(1.79f);
    }

    private double getScaleY(double value) {
        double result = proportionY(60.83f) / value; // y:396
        if (result > 1) {
            return 1;
        }
        return result;
    }

    private double getPositionY(double value) {
        double result = proportionY(60.83f) / value;
        if (result > 1) {
            return ((proportionY(63.75f) - value) / 2) + proportionY(16.59f) + value;
        }
        return proportionY(78.56f);
    }

    private void drawTextCatequesis() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout(cleanString(receiverPerson.getGroup().getCatequesis().toString()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(1.09f), proportionY(27.34f)); // y:563 w:970
        Shape shape = layout.getOutline(at);
        Color colorAwt = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        Color color = new Color(colorAwt.getRed(), colorAwt.getGreen(), colorAwt.getBlue(), 128);
        g2d.setColor(color);
        g2d.fill(shape);
    }

    private static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }

    public void draw() {
        initialize();

        drawMainRectangle();
        drawBottomPolygon1();
        drawBottomPolygon2();

        drawLogoParroquia();
        drawTextTitleParroquia();
        drawTextParroquia();
        drawQr();
        drawTextName();
        drawTextCatequesis();
        drawTextGroup();

        graphics.dispose();
    }

    public static void main(String[] args) {
        CarnetBack grafics = new CarnetBack();
        grafics.draw();
    }
}
