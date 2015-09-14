package com.piccollage.test.rect;


public class RectTest {
	
	/**
	 * 
	 * @param rect1
	 * @param rect2
	 * @return null if not overlap
	 */
	public static MyRect overlap(MyRect rect1, MyRect rect2){

		if (rect1.left < rect2.right && rect2.left < rect1.right && rect1.top < rect2.bottom && rect2.top < rect1.bottom) {
			int oleft,otop,oright,obottom;
			if (rect1.left < rect2.left){
				oleft = rect2.left;
			}else{
				oleft = rect1.left;
			}
            if (rect1.top < rect2.top){
            	otop = rect2.top;
            }else{
            	otop = rect1.top;
            }
            if (rect1.right > rect2.right){
            	oright = rect2.right;
            }else{
            	oright = rect1.right;
            }
            if (rect1.bottom > rect2.bottom){
            	obottom = rect2.bottom;
            }else{
            	obottom = rect1.bottom;
            }
            return new MyRect(oleft,otop,oright,obottom);
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		MyRect r=RectTest.overlap(new MyRect(0,2,100,50), new MyRect(6,11,33,100));
		System.out.println("r="+r);
	}

}
