package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    public Account(String userName){
        this.userName=userName;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    public void addToScore(long score) {
        this.score += score;
    }

    private long getScore() {
        return score;
    }

    private String getUserName() {
        return userName;
    }

    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }
    static void showRanking(Scene scene, Group root){
        Rectangle rectangle;
        Rectangle scoreRect;
        Text text;
        Text scoreText;
        int y = 100;
        Collections.sort(accounts);
        for(Account account : accounts){
            rectangle = new Rectangle(100,y,200,30);
            scoreRect = new Rectangle(310,y,150,30);
            rectangle.setFill(Color.rgb(100,100,100,0.5));
            scoreRect.setFill(Color.rgb(100,100,100,0.5));
            root.getChildren().add(rectangle);
            root.getChildren().add(scoreRect);
            text = new Text();
            scoreText = new Text();
            scoreText.setText(account.getScore()+"");
            text.setText(account.getUserName());
            root.getChildren().add(text);
            root.getChildren().add(scoreText);
            text.setFont(Font.font(20));
            scoreText.setFont(Font.font(20));
            scoreText.setX(315);
            scoreText.setY(y+20);
            text.setX(110);
            text.setY(y+20);
            y +=40;
        }
    }

}
