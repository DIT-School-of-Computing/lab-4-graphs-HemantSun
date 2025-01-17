package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	int mode = 0;
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;
		print(m1[0]);
		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		background(0);
		
		
	}

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}

	float sum(float[] arr) {
        float total = 0;
        for (float value : arr) {
            total += value;
        }
        return total;
    }
	
	public void draw()
	{	
		switch (mode){
			case 0:
			{
				background(0);
				float w = 420 / (float)months.length;
				float c = 255 / (float)months.length;
				for(int i = 0 ; i < months.length ;  i++)
				{
					float x = map1(i, 0, months.length, 40, 460);
					float cc = c * i;
					fill(cc, 255, 255);
					rect(x, 460, w, -rainfall[i]);
					line(x + 20, 460, x + 20, 470);
					fill(255);
					text(months[i], x + 20, 480);
				}

				for (int i = 0; i <= max(rainfall); i += 20) {
				float y = map1(i, 0, max(rainfall), 460, 40);
				line(35, y, 40, y);
				text(i, 20, y);
				}

				stroke(255);
				line(40,460,460,460);
				line(40,460,40,40);
				textAlign(CENTER, CENTER);
				text("Rainfall Bar Chart", 260, 20);
				break;
			}
			case 1:
			{
				background(0);
				for(int i = 1 ; i < months.length ;  i++)
				{
					float x1 = map1(i - 1, 0, months.length - 1, 40, 460);
					float y1 = map1(rainfall[i - 1], 0, max(rainfall), 460, 40);
					
					float x2 = map1(i, 0, months.length - 1, 40, 460);
					float y2 = map1(rainfall[i], 0, max(rainfall), 460, 40);
				
					line(x1 + 20, y1, x2 + 20, y2);
				}

				for(int i = 0 ; i < months.length ;  i++)
				{
					float x = map1(i, 0, months.length, 40, 460);
					line(x + 20, 460, x + 20, 470);
					fill(255);
					text(months[i], x + 20, 480);
				}

				for (int i = 0; i <= max(rainfall); i += 20) 
				{
					float y = map1(i, 0, max(rainfall), 460, 40);
					line(35, y, 40, y);
					text(i, 20, y);
				}

				stroke(255);
				line(40,460,460,460);
				line(40,460,40,40);
				textAlign(CENTER, CENTER);
				text("Rainfall Trend Chart", 260, 20);
				break;
			}
			case 2:
			{
				background(0);
				float diameter = 300;
				float lastAngle = 0;

				float totalRainfall = sum(rainfall);

				for (int i = 0; i < rainfall.length; i++) {
				float hue = map(i, 0, rainfall.length, 0, 255);
				fill(hue, 255, 255);

				float angle = map(rainfall[i], 0, totalRainfall, 0, TWO_PI);
				arc(width / 2, height / 2, diameter, diameter, lastAngle, lastAngle + angle);
				
				float labelAngle = lastAngle + angle / 2;
				float labelX = width / 2 + (diameter / 2 + 20) * cos(labelAngle);
				float labelY = height / 2 + (diameter / 2 + 20) * sin(labelAngle);

				fill(255);
				textAlign(CENTER, CENTER);
				text(months[i], labelX, labelY);
				lastAngle += angle;
				}
				fill(255);
				text("Rainfall piechart", 260, 30);
				break;
			}
		}
		
	}
}
