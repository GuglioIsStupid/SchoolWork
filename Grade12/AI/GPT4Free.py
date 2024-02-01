import g4f # GPT-4 Free

wholeChat = input("Input a starting message: ") + "\n"

## Normal response
response = g4f.ChatCompletion.create(
    model=g4f.models.gpt_4,
    messages=[{"role": "user", "content": wholeChat}],
)  # Alternative model setting
wholeChat += response + "\n"
while True:
    print(wholeChat)
    inputMessage = input("Input a message: ")
    wholeChat += inputMessage + "\n"
    response = g4f.ChatCompletion.create(
        model=g4f.models.gpt_4,
        messages=[{"role": "user", "content": wholeChat}],
    )
    wholeChat += "\n" + response + "\n"
    print("\033c", end="")
   