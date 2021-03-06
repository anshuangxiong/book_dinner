package com.anxiong.bookdinner.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * @author xiong
 *验证码 
 */
public class CheckNum extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 21L;
	private Font mFont=new Font("Times New Roman", Font.PLAIN, 18);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType("image/gif");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		int width=60;
		int height=20;
		ServletOutputStream out=response.getOutputStream();
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		Random random=new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.setFont(mFont);
		g.setColor(getRandColor(160, 200));
		for(int i=0;i<155;i++){
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int x1=random.nextInt(12);
			int y1=random.nextInt(12);
			g.drawLine(x, y, x1+x, y1+y);
		}
		String sRand="";
		for(int i=0;i<4;i++){
			String rand=String.valueOf(random.nextInt(10));
			sRand+=rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 13*i+6, 16);
		}
		session.setAttribute("rand", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", out);
		out.close();
		
	}
	static Color getRandColor(int fc,int bc){
		Random random=new Random();
		if(fc>255){
			fc=255;
		}
		if(bc>25){
			bc=255;
		}
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
}
