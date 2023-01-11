package org.parish.attendancesb.services.carnet;

import org.parish.attendancesb.exceptions.ImageException;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.services.barcode.Barcode;
import org.parish.attendancesb.utils.image.ImageBase64;
import org.springframework.stereotype.Component;

@Component
public class Carnet {

    private CarnetFront carnetFront;

    private CarnetBack carnetBack;

    public Carnet(CarnetFront carnetFront, CarnetBack carnetBack) {
        this.carnetFront = carnetFront;
        this.carnetBack = carnetBack;
    }

    public void setReceiverPerson(ReceiverPerson receiverPerson) {
        carnetFront.setReceiverPerson(receiverPerson);
        carnetBack.setReceiverPerson(receiverPerson);
    }

    public void draw() {
        carnetFront.draw();
        carnetBack.draw();
    }

    public String getCarnetFront() {
        try {
            return ImageBase64.encoder(carnetFront.getBufferedImage());
        } catch (Exception e) {
            throw new ImageException(e.getMessage());
        }
    }

    public String getCarnetBack() {
        try {
            return ImageBase64.encoder(carnetBack.getBufferedImage());
        } catch (Exception e) {
            throw new ImageException(e.getMessage());
        }
    }
}
