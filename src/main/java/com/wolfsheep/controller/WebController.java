package com.wolfsheep.controller;

import com.wolfsheep.model.GameState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private final GameController gameEngine;
    private Integer selectedSheepStart = null;

    public WebController(GameController gameEngine) {
        this.gameEngine = gameEngine;
    }

    @GetMapping("/")
    public String index(Model model) {
        updateModel(model, null);
        return "game";
    }

    @PostMapping("/move")
    public String move(@RequestParam("target") int target, Model model) {
        GameState state = gameEngine.getGameState();
        String message = "";

        if (state.isWolfTurn()) {
            message = gameEngine.moveWolf(target);
        } else {
            if (selectedSheepStart == null) {
                if (state.getSheepPos().contains(target)) {
                    selectedSheepStart = target;
                    message = "Sheep at " + target + " selected. Choose a destination.";
                } else {
                    message = "That's not a sheep! Please select a sheep to move.";
                }
            } else {
                if (state.getSheepPos().contains(target)) {
                    selectedSheepStart = target;
                    message = "Changed selection to sheep at " + target;
                } else {
                    message = gameEngine.moveSheep(selectedSheepStart, target);
                    if (!message.startsWith("Invalid")) {
                        selectedSheepStart = null;
                    }
                }
            }
        }

        updateModel(model, message);
        return "game";
    }

    @PostMapping("/reset")
    public String reset() {
        gameEngine.resetGame();
        selectedSheepStart = null;
        return "redirect:/";
    }

    private void updateModel(Model model, String message) {
        GameState state = gameEngine.getGameState();
        model.addAttribute("wolfPos", state.getWolfPos());
        model.addAttribute("sheepPos", state.getSheepPos());
        model.addAttribute("isWolfTurn", state.isWolfTurn());
        model.addAttribute("round", state.getRoundCounter());
        model.addAttribute("message", message);
        model.addAttribute("selectedSheep", selectedSheepStart);
        model.addAttribute("winner", state.getWinner());
    }
}
