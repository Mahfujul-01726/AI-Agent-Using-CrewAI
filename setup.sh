#!/bin/bash

# AI Agent Using CrewAI - Setup Script
# This script sets up the environment for running the AI Agent project

set -e  # Exit on error

echo "======================================"
echo "AI Agent Using CrewAI - Setup"
echo "======================================"
echo ""

# Check if Python is installed
if ! command -v python3 &> /dev/null; then
    echo "❌ Python 3 is not installed. Please install Python 3.11 or higher."
    exit 1
fi

# Check Python version
PYTHON_VERSION=$(python3 --version | cut -d' ' -f2 | cut -d'.' -f1,2)
echo "✓ Found Python $(python3 --version)"

# Check if .env file exists
if [ ! -f .env ]; then
    echo ""
    echo "⚠️  No .env file found!"
    echo "Creating .env file from .env.example..."
    
    if [ -f .env.example ]; then
        cp .env.example .env
        echo "✓ Created .env file"
        echo ""
        echo "⚠️  IMPORTANT: Please edit the .env file and add your API keys:"
        echo "   1. OPENAI_API_KEY - Get from: https://platform.openai.com/api-keys"
        echo "   2. SERPER_API_KEY - Get from: https://serper.dev/api-key"
        echo ""
    else
        echo "❌ .env.example not found. Please create a .env file manually."
        exit 1
    fi
else
    echo "✓ .env file already exists"
fi

# Create virtual environment if it doesn't exist
if [ ! -d "venv" ]; then
    echo ""
    echo "Creating virtual environment..."
    python3 -m venv venv
    echo "✓ Virtual environment created"
else
    echo "✓ Virtual environment already exists"
fi

# Activate virtual environment
echo ""
echo "Activating virtual environment..."
source venv/bin/activate

# Upgrade pip
echo ""
echo "Upgrading pip..."
pip install --upgrade pip -q

# Install dependencies
echo ""
echo "Installing dependencies from requirements.txt..."
pip install -r requirements.txt

echo ""
echo "======================================"
echo "✓ Setup completed successfully!"
echo "======================================"
echo ""
echo "Next steps:"
echo "1. Edit the .env file and add your API keys"
echo "2. Activate the virtual environment: source venv/bin/activate"
echo "3. Run the project using one of these commands:"
echo "   - python main.py              (Run the AI healthcare research)"
echo "   - python research_agents.py   (Run the interactive research agent)"
echo ""
