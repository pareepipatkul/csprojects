from django.shortcuts import render
import requests
from datetime import date
from .models import City
from .forms import CityForm

# Create your views here.
def index(request):
    # url = 'http://api.openweathermap.org/data/2.5/weather?q=las%20vegas&units=imperial&appid=74c6308964c2daaf3d4e05790cdb9cd5'
    cities = City.objects.all()

    # cities = ['Bangkok', 'Melbourne', 'Singapore']
    url = 'http://api.openweathermap.org/data/2.5/weather?q={}&units=metric&appid=74c6308964c2daaf3d4e05790cdb9cd5'

    race_cities = ['Spa', 'Monza', 'Singapore', 'Sochi', 'Suzuka', 'Mexico City', 'Austin', 'Sao Paulo', 'Abu Dhabi']
    race_dates = [date(2019, 9, 1), date(2019, 9, 8), date(2019, 9, 22), date(2019, 9, 29), date(2019, 10, 13), date(2019, 10, 27), date(2019, 11, 3), date(2019, 11, 17), date(2019, 12, 1)]

    closest_date = min(race_dates, key=lambda x: (x - date.today()))

    next_race = race_cities[race_dates.index(closest_date)]

    if request.method == 'POST':
        form = CityForm(request.POST)
        form.save()

    form = CityForm()
    weather_data = []

    for city in race_cities:
        city_weather = requests.get(url.format(city)).json()

        weather = {
            'city': city,
            'temperature': city_weather['main']['temp'],
            'description': city_weather['weather'][0]['description'],
            'icon': city_weather['weather'][0]['icon']
        }

        weather_data.append(weather)

    context = {'weather_data': weather_data, 'form': form, 'next_race': next_race}
    return render(request, 'weather/index.html', context)