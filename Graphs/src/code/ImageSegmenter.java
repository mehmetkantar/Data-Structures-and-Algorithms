package code;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import given.Image.PixelCoordinate;
import given.Image;

public class ImageSegmenter {

  // Colors to use while coloring
  private static Color[] colors = { Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY, Color.MAGENTA, Color.RED,
      Color.CYAN, Color.LIGHT_GRAY, Color.ORANGE, Color.PINK, Color.YELLOW, Color.DARK_GRAY };

  /*
   * 
   * YOU CAN ADD MORE FIELDS HERE
   * 
   */

  /**
   * Segment image by finding connected components. Pixels in same component must
   * have the same color. Coloring should be done on a new image which should be
   * returned. Note that you need to use getValidNeighbors.
   * 
   * You can use any graph traversal method you like.
   * 
   * @param epsilon
   *          - threshold value to decide connectedness of two neighboring pixels.
   */
  public Image segmentImage(Image input, double epsilon) {
    Image output = new Image(input.getHeight(), input.getWidth());
    
    // TODO: IMPLEMENT THIS
    boolean[][] visited = new boolean[input.getHeight()][input.getWidth()];

    int colorIndex = 0;
    int numColors = colors.length;

    for (int r = 0; r < input.getHeight(); ++r) {
      for (int c = 0; c < input.getWidth(); ++c) {
        if (!visited[r][c]) {
          Color currentColor = colors[colorIndex % numColors];
          BFS(input, output, visited, new PixelCoordinate(r, c), epsilon, currentColor);
          colorIndex++;
        }
      }
    }

    return output;

  }
  
  private List<PixelCoordinate> getValidNeighbors(Image input, PixelCoordinate pc) {
	    int[] dr = {-1, 1, 0, 0}; // Up, down, left, right
	    int[] dc = {0, 0, -1, 1};
	    List<PixelCoordinate> neighbors = new ArrayList<>();

	    for (int i = 0; i < 4; ++i) {
	    	int newR = pc.r + dr[i];
	    	int newC = pc.c + dc[i];

	    	if (newR >= 0 && newR < input.getHeight() && newC >= 0 && newC < input.getWidth()) {
	    		neighbors.add(new PixelCoordinate(newR, newC));
	    	}
	    }

	    return neighbors;
  }
  
  
  
  
  private void BFS(Image input, Image output, boolean[][] visited, PixelCoordinate start, double epsilon, Color color) {
	    Queue<PixelCoordinate> queue = new LinkedList<>();
	    queue.add(start);

	    while (!queue.isEmpty()) {
	    	PixelCoordinate current = queue.poll();
	    	int r = current.r;
	    	int c = current.c;

	    	if (visited[r][c]) continue;

	    	visited[r][c] = true;
	    	output.setColor(current, color);

	    	for (PixelCoordinate neighbor : getValidNeighbors(input, current)) {
	    		if (!visited[neighbor.r][neighbor.c] && isConnected(input, current, neighbor, epsilon)) {
	    			queue.add(neighbor);
	    		}
	    	}
	    }
  } 
  	
  
  
  
  private double colorDistance(Color c1, Color c2) {
	    int redDiff = c1.getRed() - c2.getRed();
	    int greenDiff = c1.getGreen() - c2.getGreen();
	    int blueDiff = c1.getBlue() - c2.getBlue();
	    return Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff);
  }
  
  
  
  
  private boolean isConnected(Image input, PixelCoordinate pc1, PixelCoordinate pc2, double epsilon) {
	    int color1 = input.getColor(pc1.r, pc1.c);
	    int color2 = input.getColor(pc2.r, pc2.c);

	    double distance = colorDistance(new Color(color1), new Color(color2));
	    return distance <= epsilon;
  }
  

  /**
   * This function iterates over the image and colors output image using "color"
   * array in circular fashion. i.e. if all colors have been used then pick the
   * first color.
   * 
   * This is given to you as an example of how to use some of the available
   * classes,
   * 
   * @param graph
   *          - image graph.
   * @param output
   *          - output segmented image.
   */
  public Image dummyIteration(Image input) {
    int colorIndx = 0;
    int numColors = colors.length;
    Image output = new Image(input.getHeight(), input.getWidth());
    
    for (int r = 0; r < input.getHeight(); ++r) {
      for (int c = 0; c < input.getWidth(); ++c) {
        // Get all possible neighbors of pixel at row r and column c for fun
        PixelCoordinate pc = new PixelCoordinate(r, c);
        
        //HINT: You need to iterate over all the some of neighbors of the current pixel here!
        
        // Get the next color in circular fashion
        Color nextColorToUse = colors[colorIndx % numColors];
        // Set the color of pixel at PixelCoordinate pc of segmentedImage to
        // nextColorToUse
        output.setColor(pc, nextColorToUse);
        // Increment color index
        ++colorIndx;
      }
    }
    return output;
  }

}
