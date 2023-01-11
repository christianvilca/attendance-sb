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
public class CarnetFront {

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

    public CarnetFront() {
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

    private void drawTitleRectangle() {
        Color color = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        GradientPaint gradient = new GradientPaint(0, 0, Color.WHITE, width / 2, 0, color);
        graphics.setPaint(gradient);
        graphics.fillRect(0, proportionY(4.3f), width, proportionY(11.98f));
    }

    private void drawBottomPolygon1() {
        Color colorAwt = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        Color color = new Color(colorAwt.getRed(), colorAwt.getGreen(), colorAwt.getBlue(), 128);
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
    }

    private void drawBottomPolygon2() {
        Color color = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
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

    private void drawPhoto() {
        BufferedImage image = ImageBase64.decodeToBufferImage(receiverPerson.getPhoto());
        graphics.drawImage(image, proportionX(4.77f), proportionY(7.37f), proportionX(36.68f), proportionY(72.2f), null);
    }

    private void drawLogoParroquia() {
        BufferedImage image = ImageBase64.decodeToBufferImage(institutionService.getRegistry().getLogo());
        graphics.drawImage(image, proportionX(51.39f), proportionY(4.45f), proportionX(9.74f), proportionY(12.29f), null);
    }

    private void drawTextTitleParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(1.9f)));
        TextLayout layout = new TextLayout(cleanString(institutionService.getRegistry().getInstitutionName()), g2d.getFont(), g2d.getFontRenderContext());
        Shape shape = layout.getOutline(AffineTransform.getTranslateInstance(proportionX(61.63f), proportionY(8.5f)));
        g2d.setStroke(new BasicStroke(proportionX(0.5f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    private void drawTextParroquia() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout(cleanString(institutionService.getRegistry().getName()), g2d.getFont(), g2d.getFontRenderContext());
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

    private void drawBarCode() {
        BufferedImage image = Barcode.generateEAN13BarcodeImageByBarcode4j(receiverPerson.getCode());
        graphics.drawImage(image, proportionX(51.89f), proportionY(59.14f), proportionX(34.29f), proportionY(15.97f), null);
    }

    private void drawTextName() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(10f)));
        TextLayout layout = new TextLayout(cleanString(receiverPerson.toString()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(getPositionX(layout.getBounds().getWidth()), proportionY(98f)); // y:563 w:970
        at.scale(getScaleX(layout.getBounds().getWidth()), 1);
        Shape shape = layout.getOutline(at);
        g2d.setStroke(new BasicStroke(proportionX(0.7f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(Color.WHITE);
        g2d.draw(shape);
        g2d.setColor(Color.BLACK);
        g2d.fill(shape);
    }

    private double getScaleX(double value) {
        double result = proportionX(96.42f) / value; //970
        if (result > 1) {
            return 1;
        }
        return result;
    }

    private double getPositionX(double value) {
        double result = proportionX(96.42f) / value;
        if (result > 1) {
            return (proportionX(100f) - value) / 2;
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
        at.setToTranslation(proportionX(93.84f), getPositionY(layout.getBounds().getWidth())); // y:563 w:970
        at.scale(1, getScaleY(layout.getBounds().getWidth()));
        at.rotate(Math.toRadians(270));
        Shape shape = layout.getOutline(at);
        Color color = ColorAwt.hex2Rgb(receiverPerson.getGroup().getColor());
        g2d.setColor(color);
        g2d.fill(shape);
    }

    private void drawLogoGroup() {
        BufferedImage image = ImageBase64.decodeToBufferImage(receiverPerson.getGroup().getLogo());
        graphics.drawImage(image, proportionX(60.73f), proportionY(17.51f), proportionX(20.08f), proportionY(36.25f), null);
    }

    private void drawTextGroup() {
        Graphics2D g2d = graphics;

        g2d.setFont(new Font("Damn Noisy Kids", Font.PLAIN, proportionX(4f)));
        TextLayout layout = new TextLayout(cleanString(receiverPerson.getGroup().toString()), g2d.getFont(), g2d.getFontRenderContext());
        AffineTransform at = new AffineTransform();
        at.setToTranslation(proportionX(97.91f), getPositionY(layout.getBounds().getWidth())); // y:563 w:970
        at.scale(1, getScaleY(layout.getBounds().getWidth()));
        at.rotate(Math.toRadians(270));
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
        drawTitleRectangle();
        drawBottomPolygon1();
        drawBottomPolygon2();

        drawPhoto();
        drawLogoParroquia();
        drawTextTitleParroquia();
        drawTextParroquia();
        drawBarCode();
        drawTextName();
        drawTextCatequesis();
        drawLogoGroup();
        drawTextGroup();

        graphics.dispose();
    }

    public static void main(String[] args) {
        CarnetFront grafics = new CarnetFront();
        grafics.draw();
    }
}
