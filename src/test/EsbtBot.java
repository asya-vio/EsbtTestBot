package test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.ChatMember;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

//import com.pengrad.telegrambot.model.request.*;
//import com.pengrad.telegrambot.model.*;

//import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
//import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;


public class EsbtBot extends TelegramLongPollingBot{

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new EsbtBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "esbtTestBot";
    }

    @Override
    public String getBotToken() {
        return "488670898:AAFUEMUabibJ2LjrmCKnTVk3kNUIMVADnUw";
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Это команда старт!");
                    System.out.println(message.getText());
                    break;
                case "Долг/Переплата":
                    sendMsg(message, "Это долг");
                    System.out.println(message.getText());
                    break;
                case "Передать показания":
                    sendMsg(message, "Это показания");
                    System.out.println(message.getText());
                    break;
                case "Мой счетчик":
                    sendMsg(message, "Это счетчик");
                    System.out.println(message.getText());
                    break;
                case "Отключить бот":
                    sendMsg(message, "Не сейчас");
                    System.out.println(message.getText());
                    break;
                default:
                    sendMsg(message, "Так делать не надо");
                    System.out.println(message.getText());
                    break;
            }
        }

    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Долг/Переплата");
        keyboardFirstRow.add("Передать показания");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Мой счетчик");
        keyboardSecondRow.add("Отключить бот");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId()); //убрано, чтобы не пересылал твое сообщение, а писал только ответ
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

   /* Keyboard forceReply = new ForceReply(); // or just new ForceReply();
    Keyboard replyKeyboardRemove = new ReplyKeyboardRemove(); // new ReplyKeyboardRemove(isSelective)

    Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
            new String[]{"first row button1", "first row button2"},
            new String[]{"second row button1", "second row button2"},
            new String[]{"second row button3", "second row button3"})
            .oneTimeKeyboard(true)   // optional
            .resizeKeyboard(true)    // optional
            .selective(true);        // optional

    Keyboard keyboard = new ReplyKeyboardMarkup(
            new KeyboardButton[]{
                    new KeyboardButton("text"),
                    new KeyboardButton("contact").requestContact(true),
                    new KeyboardButton("location").requestLocation(true)
            }
    );*/
