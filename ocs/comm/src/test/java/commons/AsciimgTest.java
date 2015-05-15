package commons;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.ocs.commons.asciimg.AsciiImageConverter;
import me.ocs.commons.asciimg.AsciiImageCache;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年3月30日 下午10:23:33
 */
public class AsciimgTest {

	public static void main(String[] args) throws IOException {
		File inputFile = new File("F://dev/DSCF8855.jpg");
		BufferedImage input = ImageIO.read(inputFile);
		Font font = new Font("Courier", Font.PLAIN,6);

		AsciiImageCache cache = AsciiImageCache.create(font);
		AsciiImageConverter imgConverter = new AsciiImageConverter(cache);
		BufferedImage output = imgConverter.convertImage(input);
		File outputfile = new File("F://dev/DSCF8855.png");
		ImageIO.write(output, "png", outputfile);

	}
}
