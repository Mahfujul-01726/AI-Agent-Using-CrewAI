# Quick Start Guide - AI Agent Using CrewAI

This guide will help you get the AI Agent project running in just a few minutes.

## ⚡ Fast Setup (3 Steps)

### Step 1: Run the Setup Script
```bash
./setup.sh
```
This will automatically:
- Create a virtual environment
- Install all dependencies
- Create a .env file

### Step 2: Add Your API Keys

Edit the `.env` file with your favorite text editor:

```bash
nano .env
```

Replace the placeholder values with your actual API keys:
```
OPENAI_API_KEY=sk-your-actual-key-here
SERPER_API_KEY=your-actual-serper-key-here
```

**Where to get API keys:**
- OpenAI: https://platform.openai.com/api-keys
- Serper: https://serper.dev/api-key

### Step 3: Run the Project

```bash
./run.sh
```

Choose option 1 or 2:
- **Option 1**: Automated AI healthcare research (runs automatically)
- **Option 2**: Interactive research agent (you choose the topic)

## 📋 What Each Option Does

### Option 1: main.py
- Automatically researches "AI in healthcare"
- Uses a Senior Researcher agent to gather information
- Uses a Writer agent to create a blog post
- Saves the result to `new-blog-post.md`

### Option 2: research_agents.py
- Asks you for a research topic
- Researches the topic using AI
- Displays the results in your terminal

## 🔧 Troubleshooting

### "command not found: ./setup.sh"
Make the script executable:
```bash
chmod +x setup.sh run.sh
```

### "ModuleNotFoundError"
Make sure you're in the virtual environment:
```bash
source venv/bin/activate
```

### "API key error"
Double-check your .env file:
- No spaces around the `=` sign
- No quotes around the keys
- Keys are not the placeholder values

## 💡 Tips

- The virtual environment needs to be activated each time you open a new terminal
- You can edit `main.py` to change the research topic from "AI in healthcare" to anything else
- Results from `main.py` are saved to `new-blog-post.md`

## 🚀 Next Steps

Once you've successfully run the project:
1. Try changing the topic in `main.py` (line 92)
2. Experiment with different prompts in `research_agents.py`
3. Explore the CrewAI documentation: https://docs.crewai.com/

## 📞 Need Help?

If you encounter issues:
1. Make sure Python 3.11+ is installed: `python3 --version`
2. Check that both API keys are valid and have credits
3. Review the full README.md for detailed troubleshooting

---

**Ready to start?** Just run `./setup.sh` and follow the steps above! 🎉
