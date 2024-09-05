import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.HashMap;

/*
CHIP-8 Specifications:
    - Direct access up to 4KB (4,096 bytes) of memory
    - 64x32 pixel monochrome display
    - Program counter (PC), points to the current instruction in memory
    - Index register (I), used to point to locations in memory
    - Stack, used to remember the current location before a jump is performed
    - 8-bit delay timer, which is decrementing at 60Hz (60 times per second) until it reaches 0
    - 8-bit sound timer, which functions like the delay timer, but gives opff a beeping sound as long as it's not zero
    - 16 8-bit data registers (V0-VF), used to store data
    - - VF register is used as a flag for some instructions
*/

// A port of my python version to learn some more java https://github.com/GuglioIsStupid/Chip8-Emulator/blob/main/chip8.py

public class Chip8 extends JPanel implements Runnable, KeyListener {
    private static final int WIDTH = 64;
    private static final int HEIGHT = 32;
    private static final int SCALE = 10;
    private static final int DISPLAY_WIDTH = WIDTH * SCALE;
    private static final int DISPLAY_HEIGHT = HEIGHT * SCALE;

    private final int[] memory = new int[4096]; // 4KB of memory
    private final int[] v = new int[16]; // 16 8-bit data registers
    private int i;
    private int pc = 0x200;
    private final int[] stack = new int[16];
    private int sp;

    private int delayTimer;
    private int soundTimer;

    private final int[] display = new int[WIDTH * HEIGHT]; // can be 0 or 1 (off or on)

    private final boolean[] keys = new boolean[16];
    private final HashMap<Integer, Integer> keyMap;

    private final int[] fontset = {
        0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
        0x20, 0x60, 0x20, 0x20, 0x70, // 1
        0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
        0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
        0x90, 0x90, 0xF0, 0x10, 0x10, // 4
        0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
        0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
        0xF0, 0x10, 0x20, 0x40, 0x40, // 7
        0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
        0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
        0xF0, 0x90, 0xF0, 0x90, 0x90, // A
        0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
        0xF0, 0x80, 0x80, 0x80, 0xF0, // C
        0xE0, 0x90, 0x90, 0x90, 0xE0, // D
        0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
        0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    };

    private int opcode;
    private boolean running = true;

    public Chip8() {
        this.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        // Load fontset into memory
        System.arraycopy(fontset, 0, memory, 0, fontset.length);

        // Setup inputs
        keyMap = new HashMap<>();
        keyMap.put(KeyEvent.VK_1, 0x1);
        keyMap.put(KeyEvent.VK_2, 0x2);
        keyMap.put(KeyEvent.VK_3, 0x3);
        keyMap.put(KeyEvent.VK_4, 0xC);
        keyMap.put(KeyEvent.VK_Q, 0x4);
        keyMap.put(KeyEvent.VK_W, 0x5);
        keyMap.put(KeyEvent.VK_E, 0x6);
        keyMap.put(KeyEvent.VK_R, 0xD);
        keyMap.put(KeyEvent.VK_A, 0x7);
        keyMap.put(KeyEvent.VK_S, 0x8);
        keyMap.put(KeyEvent.VK_D, 0x9);
        keyMap.put(KeyEvent.VK_F, 0xE);
        keyMap.put(KeyEvent.VK_Z, 0xA);
        keyMap.put(KeyEvent.VK_X, 0x0);
        keyMap.put(KeyEvent.VK_C, 0xB);
        keyMap.put(KeyEvent.VK_V, 0xF);
    }

    private void loadRom(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = fis.readAllBytes();
        for (int i = 0; i < data.length; i++) {
            memory[0x200 + i] = data[i] & 0xFF;
        }
        fis.close();
    }

    private void emulateCycle() {
        // Fetch opcode
        opcode = (memory[pc] << 8) | memory[pc + 1];

        // Decode opcode
        int x = (opcode & 0x0F00) >> 8;
        int y = (opcode & 0x00F0) >> 4;

        switch (opcode & 0xF000) {
            case 0x0000:
                if (opcode == 0x00E0) { // Clear the display (00E0)
                    for (int i = 0; i < display.length; i++) {
                        display[i] = 0;
                    }
                    pc += 2;
                } else if (opcode == 0x00EE) { // Return from a subroutine (00EE)
                    pc = stack[sp--];
                    pc += 2;
                }
                break;

            case 0x1000: // Jump to address NNN (1NNN)
                pc = opcode & 0x0FFF;
                break;

            case 0x2000: // Call subroutine at NNN (2NNN)
                stack[++sp] = pc;
                pc = opcode & 0x0FFF;
                break;

            case 0x3000: // Skip next instruction if Vx == NN (3XNN)
                if (v[x] == (opcode & 0x00FF)) {
                    pc += 4;
                } else {
                    pc += 2;
                }
                break;

            case 0x4000: // Skip next instruction if Vx != NN (4XNN)
                if (v[x] != (opcode & 0x00FF)) {
                    pc += 4;
                } else {
                    pc += 2;
                }
                break;

            case 0x5000: // Skip next instruction if Vx == Vy (5XY0)
                if (v[x] == v[y]) {
                    pc += 4;
                } else {
                    pc += 2;
                }
                break;

            case 0x6000: // Set Vx = NN (6XNN)
                v[x] = opcode & 0x00FF;
                pc += 2;
                break;

            case 0x7000: // Set Vx = Vx + NN (7XNN)
                v[x] += opcode & 0x00FF;
                pc += 2;
                break;

            case 0x8000:
                switch (opcode & 0x000F) {
                    case 0x0000: // Set Vx = Vy (8XY0)
                        v[x] = v[y];
                        pc += 2;
                        break;
                    case 0x0001: // Set Vx = Vx OR Vy (8XY1)
                        v[x] |= v[y];
                        pc += 2;
                        break;
                    case 0x0002: // Set Vx = Vx AND Vy (8XY2)
                        v[x] &= v[y];
                        pc += 2;
                        break;
                    case 0x0003: // Set Vx = Vx XOR Vy (8XY3)
                        v[x] ^= v[y];
                        pc += 2;
                        break;
                    case 0x0004: // Set Vx = Vx + Vy, set VF = carry (8XY4)
                        v[0xF] = (v[x] + v[y] > 0xFF) ? 1 : 0;
                        v[x] += v[y];
                        pc += 2;
                        break;
                    case 0x0005: // Set Vx = Vx - Vy, set VF = NOT borrow (8XY5)
                        v[0xF] = (v[x] > v[y]) ? 1 : 0;
                        v[x] -= v[y];
                        pc += 2;
                        break;
                    case 0x0006: // Set Vx = Vx SHR 1 (8XY6)
                        v[0xF] = v[x] & 0x1;
                        v[x] >>= 1;
                        pc += 2;
                        break;
                    case 0x0007: // Set Vx = Vy - Vx, set VF = NOT borrow (8XY7)
                        v[0xF] = (v[y] > v[x]) ? 1 : 0;
                        v[x] = v[y] - v[x];
                        pc += 2;
                        break;
                    case 0x000E: // Set Vx = Vx SHL 1 (8XYE)
                        v[0xF] = (v[x] >> 7) & 0x1;
                        v[x] <<= 1;
                        pc += 2;
                        break;
                }
                break;

            case 0x9000: // Skip next instruction if Vx != Vy (9XY0)
                if (v[x] != v[y])
                if (v[x] != v[y]) {
                    pc += 4;
                } else {
                    pc += 2;
                }
                break;

            case 0xA000: // Set I = NNN (ANNN)
                i = opcode & 0x0FFF;
                pc += 2;
                break;

            case 0xB000: // Jump to location NNN + V0 (BNNN)
                pc = (opcode & 0x0FFF) + v[0];
                break;

            case 0xC000: // Set Vx = random byte AND NN (CXNN)
                v[x] = (new Random().nextInt(256)) & (opcode & 0x00FF);
                pc += 2;
                break;

            case 0xD000: // Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision (DXYN)
                int height = opcode & 0x000F;
                v[0xF] = 0;
                for (int yline = 0; yline < height; yline++) {
                    int pixel = memory[i + yline];
                    for (int xline = 0; xline < 8; xline++) {
                        if ((pixel & (0x80 >> xline)) != 0) {
                            int xPos = (v[x] + xline) % WIDTH;
                            int yPos = (v[y] + yline) % HEIGHT;
                            int index = xPos + (yPos * WIDTH);
                            if (display[index] == 1) {
                                v[0xF] = 1;
                            }
                            display[index] ^= 1;
                        }
                    }
                }
                pc += 2;
                break;

            case 0xE000:
                switch (opcode & 0x00FF) {
                    case 0x009E: // Skip next instruction if key with the value of Vx is pressed (EX9E)
                        if (keys[v[x]]) {
                            pc += 4;
                        } else {
                            pc += 2;
                        }
                        break;
                    case 0x00A1: // Skip next instruction if key with the value of Vx is not pressed (EXA1)
                        if (!keys[v[x]]) {
                            pc += 4;
                        } else {
                            pc += 2;
                        }
                        break;
                }
                break;

            case 0xF000:
                switch (opcode & 0x00FF) {
                    case 0x0007: // Set Vx = delay timer value (FX07)
                        v[x] = delayTimer;
                        pc += 2;
                        break;
                    case 0x000A: // Wait for a key press, store the value of the key in Vx (FX0A)
                        boolean keyPressed = false;
                        for (int i = 0; i < 16; i++) {
                            if (keys[i]) {
                                v[x] = i;
                                keyPressed = true;
                                break;
                            }
                        }
                        if (!keyPressed) {
                            return;
                        }
                        pc += 2;
                        break;
                    case 0x0015: // Set delay timer = Vx (FX15)
                        delayTimer = v[x];
                        pc += 2;
                        break;
                    case 0x0018: // Set sound timer = Vx (FX18)
                        soundTimer = v[x];
                        pc += 2;
                        break;
                    case 0x001E: // Set I = I + Vx (FX1E)
                        i += v[x];
                        pc += 2;
                        break;
                    case 0x0029: // Set I = location of sprite for digit Vx (FX29)
                        i = v[x] * 5;
                        pc += 2;
                        break;
                    case 0x0033: // Store BCD representation of Vx in memory locations I, I+1, and I+2 (FX33)
                        memory[i] = v[x] / 100;
                        memory[i + 1] = (v[x] / 10) % 10;
                        memory[i + 2] = v[x] % 10;
                        pc += 2;
                        break;
                    case 0x0055: // Store registers V0 through Vx in memory starting at location I (FX55)
                        for (int j = 0; j <= x; j++) {
                            memory[i + j] = v[j];
                        }
                        pc += 2;
                        break;
                    case 0x0065: // Read registers V0 through Vx from memory starting at location I (FX65)
                        for (int j = 0; j <= x; j++) {
                            v[j] = memory[i + j];
                        }
                        pc += 2;
                        break;
                }
                break;

            default:
                System.out.println("Unknown opcode: " + Integer.toHexString(opcode));
                running = false;
                break;
        }
    }

    private void drawGraphics(Graphics g) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (display[x + y * WIDTH] == 1) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
            }
        }
    }

    private void timers() {
        if (delayTimer > 0) {
            delayTimer--;
        }

        if (soundTimer > 0) {
            if (soundTimer == 1) {
                Toolkit.getDefaultToolkit().beep();
            }
            soundTimer--;
        }
    }

    @Override
    public void run() {
        while (running) {
            emulateCycle();
            repaint();
            timers();

            try {
                Thread.sleep(1000 / 120); // 120Hz
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraphics(g);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        Integer chip8Key = keyMap.get(key);
        if (chip8Key != null) {
            keys[chip8Key] = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Integer chip8Key = keyMap.get(key);
        if (chip8Key != null) {
            keys[chip8Key] = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("CHIP-8 Emulator");
        Chip8 chip8 = new Chip8();
        frame.add(chip8);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        try {
            chip8.loadRom("Bored/testroms/pong.ch8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Thread(chip8).start();
    }
}
