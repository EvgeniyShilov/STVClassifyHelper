import javax.swing.*;
import java.awt.*;

class ImageView extends JFrame {

    private Image image;

    ImageView(Image image) {
        this(image, null);
    }

    ImageView(Image image, String title) {
        this.image = image;
        setBounds(0, 0, image.getRaw().getWidth(), image.getRaw().getHeight() + 32);
        if (title != null) setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        if (image != null) graphics.drawImage(image.getRaw(), 0, 32, this);
    }
}