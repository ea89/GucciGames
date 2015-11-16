package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DisplayChat  implements DisplayComponent{
        private VBox chat;
        private TextArea inputArea;
        private ListView<String> chatHistory;
        
        public DisplayChat() {
            chat = new VBox();
            inputArea=new TextArea();
            inputArea.setPromptText("Click to chat");
            //inputArea.setMaxWidth(200);
            inputArea.prefHeightProperty().set(100);
            chatHistory=new ListView<String>();
            
            chat.getChildren().add(chatHistory);
            chat.getChildren().add(inputArea);
            chat.getStyleClass().add("vbox-chat");
            }
    
	@Override
	public Node getNodeToDraw() {
		return chat;
	}

}