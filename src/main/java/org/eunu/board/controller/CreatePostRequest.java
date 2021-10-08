package org.eunu.board.controller;

public record CreatePostRequest(String title, Integer userId, String content) {

}
