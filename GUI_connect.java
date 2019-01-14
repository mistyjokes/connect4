package connect4_Project2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI_connect extends JFrame implements TheGame, ActionListener{

	private JPanel jpmain, jpBoard, jpIO, ejp, sjp, wjp, jpButton;
	private JLabel displayOut;
	private JButton [][] board;
	private String currPlayer = "X";
	private int row = 6;
	private int column = 7;
	private int [] nextLabelRowInCol = {5,5,5,5,5,5,5};
	private ImageIcon IM1 = new ImageIcon("luffy1.jpg");
	private ImageIcon IM2 = new ImageIcon("ace.jpg");
	
	
	public GUI_connect() {
		setTitle("CONNECT 4");
		jpmain = new JPanel();
		jpmain.setLayout(new BorderLayout());
	
		displayOut = new JLabel("Let's Play "+currPlayer+ " goes first");
		jpIO = new JPanel();
		jpIO.add(displayOut);
		jpmain.add(jpIO, BorderLayout.NORTH);
		
		jpButton = new BtnPanel();
		jpButton.setLayout(new GridLayout(1,7));
		jpmain.add(jpButton, BorderLayout.NORTH);
		
		jpBoard = new JPanel();
		jpBoard.setLayout(new GridLayout(6,7));
		displayBoard();
		jpmain.add(jpBoard, BorderLayout.CENTER);
		
		ejp = new JPanel();
		jpmain.add(ejp, BorderLayout.EAST);
		
		sjp = new JPanel();
		jpmain.add(sjp, BorderLayout.SOUTH);
		
		wjp = new JPanel();
		jpmain.add(wjp, BorderLayout.WEST);
		
		add(jpmain);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,700);
		setVisible(true);
	
	
}
	
	private class BtnPanel extends JPanel implements ActionListener{
		private String [] btnFaces = {"1" , "2", "3", "4", "5", "6", "7"};
		private JButton [] jbtns;
		
		public BtnPanel(){
			setLayout(new GridLayout(1,btnFaces.length));
			jbtns = new JButton[btnFaces.length];
			populatePanelWithButtons();
		}
		
		private void populatePanelWithButtons(){
			for(int i=0; i<jbtns.length; i++){
				jbtns[i] = new JButton(btnFaces[i]);
				jbtns[i].addActionListener(this);
				add(jbtns[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnClicked = (JButton) e.getSource();
			for(int col = 0; col < jbtns.length; col++){
				if(btnClicked.equals(jbtns[col])){
					displayOut.setText(currPlayer);
					int rowToUpdate = nextLabelRowInCol[col];
					
					board[rowToUpdate][col].setText(currPlayer);
					
					nextLabelRowInCol[col]--;
				}
			}
			
			displayWinner();
			updateCurrPlayer();
			
		}
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void displayBoard() {
		
		
		board = new JButton[6][7];
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board[row][col] = new JButton();
				board[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				board[row][col].addActionListener(this);
				board[row][col].setEnabled(true);
				jpBoard.add(board[row][col]);
			}
		}
		
	}

	@Override
	public void clearBoard() {
		
		
		
		for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[row].length; col++) {
				board[row][col].setText("");
				board[row][col].setEnabled(true);
			}
		}
		
		for(int i=0; i<nextLabelRowInCol.length; i++) {
			nextLabelRowInCol[i]=5;
		}
		
		/*for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[row].length; col++) {
			
			}*/
		}

	@Override
	public void displayWinner() {
		
		
		
		if( checkMainDiagonal() || checkSecondaryDiagonal() || checkRow() || checkColumn()  ){
			
			
		
			int dialogButton = JOptionPane.showConfirmDialog(null, "WINNER IS "+ currPlayer+"! Do you want to play again? Yes or No" , currPlayer, JOptionPane.YES_NO_OPTION); 
		
			if(dialogButton != JOptionPane.YES_NO_OPTION) {
				return;
				
				
			} else {
				clearBoard();
				
			}
		
		
		}
		else{
			if(isFull()){
				int dialogButton = JOptionPane.showConfirmDialog(null, "DRAW! Do you want to play again? Yes or No");
				
				if(dialogButton != JOptionPane.YES_NO_OPTION) {
					return;
					
					
				} else {
					clearBoard();
					
				}
			}
		}
		
		
	}

	
	@Override
	public void updateCurrPlayer() {
		if(currPlayer.equalsIgnoreCase("X")){
			currPlayer = "O";
		}
		else{
			currPlayer = "X";
		}
		
	}

	@Override
	public boolean isFull() {
	
		
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				if(board[row][col].getText().isEmpty()){
					return false;
				}
			}
		}
		return true;
	}
	
	
	private boolean checkMainDiagonal(){ 
		int diagCount = 0;
		for(int row=0; row < 3; row++){
			for(int col=0; col < 4; col++) {
			if(board[row][col].getText().contains(currPlayer) && board[row+1][col+1].getText().contains(currPlayer)
					&& board[row+2][col+2].getText().contains(currPlayer) && board[row+3][col+3].getText().contains(currPlayer) ){
				
					return true;
				
			}
		  }
		}
		return false;
	}
	private boolean checkSecondaryDiagonal(){ 
		int diagCount = 0;
		
		for(int row = 3; row<6; row++ ){
			for(int col =0; col<4; col++) {
			if(board[row][col].getText().contains(currPlayer) && board[row-1][col+1].getText().contains(currPlayer)
					&& board[row-2][col+2].getText().contains(currPlayer) && board[row-3][col+3].getText().contains(currPlayer)){
				
					return true;
				
			}
		}
			
		}
		return false;
	}


	private boolean checkColumn(){ 
		int rowCount = 0;
		int colCount = 0;
		
		for(int row = 0; row <3; row++){
			for(int col = 0; col <7;col++) {
				if(board[row][col].getText().contains(currPlayer) && board[row+1][col].getText().contains(currPlayer)
						&& board[row+2][col].getText().contains(currPlayer) && board[row+3][col].getText().contains(currPlayer)){
					
						return true;
					
				
				}
			  }
				
			}
			return false;
	
	}  
	
	
	private boolean checkRow(){
		int rowCount = 0; 
		int colCount = 0;
		
		for(int row = 0; row <board.length; row++ ) {
			for(int col=0; col<4; col++){
				if(board[row][col].getText().contains(currPlayer) && board[row][col+1].getText().contains(currPlayer)
						&& board[row][col+2].getText().contains(currPlayer) && board[row][col+3].getText().contains(currPlayer) ){
					
						return true;
					
				}
			  }
			
			 
			}
			return false;
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
	

	
























}


 


