#!/bin/bash

# AI Agent Using CrewAI - Run Script
# This script helps run the AI Agent project with proper error handling

set -e  # Exit on error

echo "======================================"
echo "AI Agent Using CrewAI - Run"
echo "======================================"
echo ""

# Check if virtual environment exists
if [ ! -d "venv" ]; then
    echo "❌ Virtual environment not found!"
    echo "Please run ./setup.sh first to set up the environment."
    exit 1
fi

# Check if .env file exists
if [ ! -f .env ]; then
    echo "❌ .env file not found!"
    echo "Please create a .env file with your API keys."
    echo "You can copy .env.example to .env and edit it."
    exit 1
fi

# Activate virtual environment
source venv/bin/activate

# Display menu
echo "Select which script to run:"
echo ""
echo "1) main.py - Run AI healthcare research (automated)"
echo "2) research_agents.py - Run interactive research agent"
echo ""
read -p "Enter your choice (1 or 2): " choice

echo ""

case $choice in
    1)
        echo "Running main.py..."
        echo "======================================"
        python main.py
        ;;
    2)
        echo "Running research_agents.py..."
        echo "======================================"
        python research_agents.py
        ;;
    *)
        echo "❌ Invalid choice. Please run the script again and select 1 or 2."
        exit 1
        ;;
esac

echo ""
echo "======================================"
echo "✓ Execution completed"
echo "======================================"
