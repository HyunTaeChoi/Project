package kr.ac.ht.project.lecture1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class NumberGame extends JFrame{
	private int randomNumber;
	private int num;
	private String n;

	private JTextField tf = new JTextField(10);
	private Vector<String> v = new Vector<String>();
	private JList<String> tl = new JList<String>();
	
	public NumberGame() {
		setTitle("숫자게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		Container contentsPane = getContentPane();
		contentsPane.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(310, 50));
		JPanel panel2 = new JPanel();
		
		JLabel la = new JLabel("숫자를 추정하세요");
		panel1.add(la);
		panel1.add(tf);
		JPanel panel = new JPanel();
		JScrollPane jp = new JScrollPane(tl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(jp);
		jp.setPreferredSize(new Dimension(300, 100));
		
		JLabel output = new JLabel("정답을 입력하세요", JLabel.CENTER);
		output.setPreferredSize(new Dimension(300, 50));
		JTextArea manual = new JTextArea("");
		manual.setPreferredSize(new Dimension(250, 250));
		manual.setLineWrap(true);
		manual.append("1~100 까지의 숫자를 입력하세요\n\n");
		manual.append("입력한숫자가 정답숫자보다 30 높으면\n");
		manual.append("입력한숫자가 너무 높습니다\n\n");
		manual.append("정답숫자가 입력한숫자보다 30 높으면\n");
		manual.append("입력한숫자가 너무 낮습니다\n\n");
		manual.append("입력한숫자가 정답숫자보다 높으면\n");
		manual.append("입력한숫자가 높습니다\n\n");
		manual.append("정답숫자가 입력한숫자보다 30 높으면\n");
		manual.append("입력한숫자가 낮습니다\n\n");
		manual.setEditable(false);
		
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) e.getSource();
				v.add(t.getText());
				tl.setListData(v);
				n = v.size() + "";
				if(t == tf) {
					num = Integer.parseInt(((JTextField)tf).getText());
					int ranMnum = randomNumber - num;
					//입력한 숫자가 랜덤숫자보다 낮을경우
					int numMran = num - randomNumber;
					//입력한 숫자가 랜덤숫자보다 높을경우
					if(ranMnum >= 30) {
						output.setText("입력한숫자가 너무 낮습니다");
						}
					if (ranMnum < 30 && ranMnum > 0) {
						output.setText("입력한숫자가 낮습니다");
					}
					if (numMran >= 30) {
						output.setText("입력한숫자가 너무 높습니다");
					}
					if (numMran < 30 && numMran > 0) {
						output.setText("입력한숫자가 높습니다");
					}
					if (num == randomNumber) {
						output.setText(n +"번 만에 정답을 맞췄습니다.");
						int result = JOptionPane.showConfirmDialog(null, "다시시작하시겠습니까?", "재시작", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.CLOSED_OPTION) {}
						if(result == JOptionPane.YES_NO_OPTION) {ran();}
						if(result == JOptionPane.NO_OPTION) {}
					}
				}
				tf.setText("");
			}
		});
		
		JButton restartbt = new JButton("재시작");
		restartbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ran();
			}
		});
		JButton closebt = new JButton("종료");
		closebt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {}
				else if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else {}
			}
		});
		panel2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		panel2.add(restartbt);
		panel2.add(closebt);
		
		contentsPane.add(panel1);
		contentsPane.add(panel);
		contentsPane.add(output);
		contentsPane.add(manual);
		contentsPane.add(panel2);
		
		ran();
		setSize(330, 580);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		NumberGame frame = new NumberGame();
	}
	public void ran() {
		v.removeAllElements();
		randomNumber = (int)(Math.random()*100) + 1;
		System.out.println("생성된 숫자 : " + randomNumber);
	}
}
