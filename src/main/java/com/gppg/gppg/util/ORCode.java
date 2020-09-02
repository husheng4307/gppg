package com.gppg.gppg.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

/**
 * @Author: lixinjian
 * @Description: 生成二维码工具类
 * @Date: 8:57 2019/9/26
 */
public class ORCode {

	//设置数据的格式(utf-8) gbk ios8859-1
	private static final String CHARSET = "utf-8";
	//设置图片类型为JPG
	private static final String FORMAT_NAME = "JPG";
	//二维码的尺寸
	private static final int ORCODE_SIZE = 600;
	//LOGO的宽度
	private static final int WIDTH = 80;
	//LOGO的高度
	private static final int HEIGHT = 80;

	//生成二维码
	/**
	 *
	 * @param content 包含的文字信息
	 * @param imgPath 图片的路径
	 * @param needCompress 是否压缩图片
	 * @return 生成好的二维码
	 * @throws WriterException
	 * @throws IOException
	 */
	private static BufferedImage createImage(String content,
											 String imgPath,
											 boolean needCompress) throws WriterException, IOException {
		Hashtable<EncodeHintType,Object> hints = new Hashtable
				<EncodeHintType,Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION,
				ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(
				content,BarcodeFormat.QR_CODE,
				ORCODE_SIZE,ORCODE_SIZE,hints);

		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();

		BufferedImage image = new BufferedImage(
				width, height, BufferedImage.TYPE_INT_RGB);

		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				image.setRGB(x, y, bitMatrix.get(x, y)
						? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		//生成一个不带LOGO的二维码
		if(imgPath == null || "".equals(imgPath)){
			return image;
		}
		//生成一个带LOGO的二维码
		ORCode.insertImage(image, imgPath, needCompress);
		return image;
	}

	/**
	 *
	 * @param image 二维码图片
	 * @param imgPath Logo的图片地址
	 * @param needCompress 是否压缩
	 * @throws IOException
	 */
	private static void insertImage(BufferedImage image,
									String imgPath,
									boolean needCompress) throws IOException{
		File file = new File(imgPath);
		if(!file.exists()){
			System.err.println(imgPath+" 该文件不存在");
			return;
		}

		Image img = ImageIO.read(new File(imgPath));

		int width = img.getWidth(null);
		int height = img.getHeight(null);

		if(needCompress){
			//压缩LOGO
			if(width > WIDTH){
				width = WIDTH;
			}
			if(height > HEIGHT){
				height = HEIGHT;
			}

			Image image2 = img.getScaledInstance(width,
					height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image2,0,0,null); //绘制缩小后的图
			g.dispose();
			img = image2;
		}
		//插入LOGO
		Graphics2D graph = image.createGraphics();
		int x =(ORCODE_SIZE - width)/2;
		int y =(ORCODE_SIZE - height)/2;

		graph.drawImage(img,x,y,width,height,null);
		Shape shape = new RoundRectangle2D.Float(x,y,
				width,height,6,6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	public static void main(String[] args) throws WriterException, IOException {
		//二维码最终显示的信息 url地址，跳转至页面，由页面进行相应操作
		String text = "http://www.baidu.com";
		//调用生成二维码的方法  "F:/MyJava/lianxi/OR/1.jpg" 二维码中间图片的地址
		BufferedImage img = ORCode.createImage(text, "F:/MyJava/lianxi/OR/1.jpg", true);
		//生成好二维码后存放的地址  "E:/MyJava/lianxi/OR"
		String filepath = "E:/MyJava/lianxi/OR";
		File file = new File(filepath);
		//当文件夹不存在时，自动创建多层目录
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}

		String orname = new Random().nextInt(9999999)+".jpg";
		ImageIO.write(img, FORMAT_NAME,
				new File(filepath+"/"+orname));
	}
}
