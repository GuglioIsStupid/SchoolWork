function love.load()
    hexrgb = require("hexrgb")
    hexColours = {
        "#ff0000",
        "#00ff00",
        "#0000ff",
        "#ffff00",
        "#00ffff",
        "#ff00ff",
        "#ffffff",
        "#000000"
    }
    selectedColour = hexColours[love.math.random(1, #hexColours)]
end

function love.update(dt)
end

function love.keypressed(key)
    if key == "space" then
        selectedColour = hexColours[love.math.random(1, #hexColours)]
    end
end

function love.draw()
    love.graphics.print(hexrgb.hex2rgb(selectedColour), 10, 10)
    love.graphics.print(hexrgb.rgb2hex(1, 0, 0), 10, 20)

    love.graphics.setColor(hexrgb.hex2rgb(selectedColour))
    love.graphics.rectangle("fill", 10, 50, 100, 100) 
    love.graphics.setColor(1, 1, 1)
end