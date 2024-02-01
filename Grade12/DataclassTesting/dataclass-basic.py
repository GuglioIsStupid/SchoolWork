# import a dataclass module
from dataclasses import dataclass, field

@dataclass
class Person:
    name: str
    age: int
    # non init field "greeting"
    greeting: str = field(init=False)

    def __post_init__(self):
        self.greeting = f"Hello, my name is {self.name} and I am {self.age} years old."

# create an instance of the class
t_Person = Person("John", 30)
print(t_Person.greeting) 