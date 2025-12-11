import os
import sys
from dotenv import load_dotenv
from crewai import Agent, Task, Crew
from crewai_tools import SerperDevTool
from langchain_openai import ChatOpenAI

load_dotenv()

SERPER_API_KEY = os.getenv("SERPER_API_KEY")
OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")

# Validate API keys
if not SERPER_API_KEY or SERPER_API_KEY == "your_serper_api_key_here":
    print("❌ Error: SERPER_API_KEY is not set or is using the default placeholder value.")
    print("Please edit the .env file and add your Serper API key.")
    print("Get your API key from: https://serper.dev/api-key")
    sys.exit(1)

if not OPENAI_API_KEY or OPENAI_API_KEY == "your_openai_api_key_here":
    print("❌ Error: OPENAI_API_KEY is not set or is using the default placeholder value.")
    print("Please edit the .env file and add your OpenAI API key.")
    print("Get your API key from: https://platform.openai.com/api-keys")
    sys.exit(1)

print("✓ API keys loaded successfully\n")

search_tool = SerperDevTool()

def create_research_agent():

    llm = ChatOpenAI(model="gpt-3.5-turbo")
  
    return Agent(
        role="Research Specialist",
        goal="Conduct thorough research on given topics",
        backstory="You are an experienced researcher with expertise in finding and synthesizing information from various sources",
        verbose=True,
        allow_delegation=False,
        tools=[search_tool],
        llm=llm,
    )




def create_research_task(agent, topic):
    return Task(
        description=f"Research the following topic and provide a comprehensive summary: {topic}",
        agent=agent,
        expected_output="A detailed summary of the research findings, including key points and insights related to the topic"
    )

def run_research(topic):
    agent = create_research_agent()
    task = create_research_task(agent, topic)
    crew = Crew(agents=[agent], tasks=[task])
    result = crew.kickoff()
    return result

if __name__ == "__main__":
    print("Welcome to the Research Agent!")
    topic = input("Enter the research topic: ")
    result = run_research(topic)
    print("Research Result:")
    print(result)